// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PurchaseOrderService} from "../../../_service/purchase-order.service";
import {SupplierService} from "../../../_service/supplier.service";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  order
  orderDetails = []
  total = 0

  constructor(private router: Router, private supplierService: SupplierService) {
  }

  ngOnInit(): void {
    this.getPurchaseOrderDetails()
    this.calcTotal()
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

  calcTotal() {
    this.total = 0
    for (let orderDetail of this.orderDetails) {
      this.total += (orderDetail.soUnitPrice * orderDetail.soQuantity)
    }
    this.supplierService.order.poTotal = this.total
  }

  deliverOrder(){
    this.supplierService.deliverOrder(this.order.id).subscribe(()=>{
      this.router.navigate(['/purchase_orders'])
    })
  }
}
