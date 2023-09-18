// @ts-nocheck
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {SupplierService} from "../../../_service/supplier.service";

@Component({
  selector: 'app-view-quotation-details-s',
  templateUrl: './view-quotation-details-s.component.html',
  styleUrls: ['./view-quotation-details-s.component.css']
})
export class ViewQuotationDetailsSComponent implements OnInit {

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

  finalizeQuotation() {
    let purchaseOrderDetails = [];
    for (let orderDetail of this.order.purchaseOrderDetailList) {
      purchaseOrderDetails.push(orderDetail)
    }
    this.order.purchaseOrderDetails = purchaseOrderDetails
    this.supplierService.finalizeQuotation(this.order).subscribe(() => {
      this.order.poFinalized = true;
      this.router.navigate(['/view_quotations_s'])
    })
  }

  totalR = 0
  totalN = 0
  calcTotal() {
    this.totalR = 0
    this.totalN = 0
    for (let orderDetail of this.orderDetails) {
      this.totalR += (orderDetail.poUnitPrice * orderDetail.poQuantity)
      this.totalN += (orderDetail.soUnitPrice * orderDetail.soQuantity)
    }
  }

}
