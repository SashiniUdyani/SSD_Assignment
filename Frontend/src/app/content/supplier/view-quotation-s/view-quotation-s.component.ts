// @ts-nocheck
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {SupplierService} from "../../../_service/supplier.service";
import {AlertBoxService} from "../../../alert-box/alert-box.service";

@Component({
  selector: 'app-view-quotation-s',
  templateUrl: './view-quotation-s.component.html',
  styleUrls: ['./view-quotation-s.component.css']
})
export class ViewQuotationSComponent implements OnInit {

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

  // isModalTableDetails = {
  //   text: '',
  //   openTable: false,
  //   foundLetter: ''
  // };

  constructor(private router: Router, private supplierService: SupplierService, private alertService: AlertBoxService) {
  }

  ngOnInit(): void {
    this.getPurchaseOrders()
  }

  reOrderPumps() {

  }

  reOrderConsumes() {

  }

  viewPO(order) {
    // order.poFinalized = false;
    // order.poAccepted = false;
    this.supplierService.order = order
    this.router.navigate(['/view_quotation_details_s'])
  }

  getPurchaseOrders() {
    this.supplierService.getQuotations().subscribe(orders => {
      console.log(orders)
      this.orders = orders
    })
  }

  calcTotalP(order) {
    let total = 0
    for (let orderDetail of order.purchaseOrderDetailList) {
      total += (orderDetail.poUnitPrice * orderDetail.poQuantity)
    }
    return total;
  }

  calcTotalS(order) {
    let total = 0
    for (let orderDetail of order.purchaseOrderDetailList) {
      total += (orderDetail.soUnitPrice * orderDetail.soQuantity)
    }
    return total;
  }
  // isTrueOrFalseDetails(reply) {
  //   this.isModalTableDetails.openTable = reply;
  // }
}
