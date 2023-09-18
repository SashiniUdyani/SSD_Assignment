// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {PurchaseOrderService} from "../../../_service/purchase-order.service";
import {Router} from "@angular/router";
import {SupplierService} from "../../../_service/supplier.service";

@Component({
  selector: 'app-purchase-order-details',
  templateUrl: './purchase-order-details.component.html',
  styleUrls: ['./purchase-order-details.component.css']
})
export class PurchaseOrderDetailsComponent implements OnInit {

  orders = [];
  consumption = 0;
  consumed = 0;
  trip;
  fuelConsumptions = []
  selectedOrderBy1 = ''
  selectedOrderBy2 = ''

  alertBox = {
    alert: false,
    msg: '',
    value: ''
  };

  order
  orderDetails = []
  total = 0

  constructor(private router: Router, private supplierService: SupplierService) {
  }

  ngOnInit(): void {
    this.getPurchaseOrderDetails()
    this.calcTotal()
  }

  acceptOrder() {
    this.supplierService.acceptOrder(this.order.id).subscribe(() => {
      this.order.poAccepted = true
    })
  }

  reOrderConsumes() {

  }

  getPurchaseOrderDetails() {
    this.order = this.supplierService.order
    this.orderDetails = this.supplierService.order.purchaseOrderDetailList
    // console.log(this.orderDetails)
    // for (let orderDetail of this.orderDetails) {
    //   orderDetail.soUnitPrice = orderDetail.poUnitPrice
    //   orderDetail.soQuantity = orderDetail.poQuantity
    // }
    // console.log(this.order)
  }

  viewInvoice() {
    this.router.navigate(['/view_invoice'])
  }

  // changeStatus() {
  //   this.alertBox.alert = true;
  //   this.alertBox.msg = 'Do you want to accept the order?';
  //   this.alertService.reply.observers = [];
  //   this.alertService.reply.subscribe(reply => {
  //     if (reply) {
  //       // this.customerS.deleteFuelConsumption(id).subscribe(() => {
  //       //   this.getFuelConsumptions()
  //       // })
  //     }
  //     this.alertBox.alert = false;
  //   })
  // }

  finalizeOrder() {
    let purchaseOrderDetails = [];
    for (let orderDetail of this.order.purchaseOrderDetailList) {
      purchaseOrderDetails.push(orderDetail)
    }
    this.order.purchaseOrderDetails = purchaseOrderDetails
    this.supplierService.finalizePurchaseOrder(this.order).subscribe(() => {
      this.order.poFinalized = true;
    })
  }

  calcTotal() {
    this.total = 0
    for (let orderDetail of this.orderDetails) {
      this.total += (orderDetail.soUnitPrice * orderDetail.soQuantity)
    }
    this.supplierService.order.poTotal = this.total
  }
}
