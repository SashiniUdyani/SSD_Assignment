// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PurchaseOrderService} from "../../../_service/purchase-order.service";
import {AlertBoxService} from "../../../alert-box/alert-box.service";
import {SupplierService} from "../../../_service/supplier.service";

@Component({
  selector: 'app-purchase-orders',
  templateUrl: './purchase-orders.component.html',
  styleUrls: ['./purchase-orders.component.css']
})
export class PurchaseOrdersComponent implements OnInit {

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
    this.router.navigate(['/purchase_order_details'])
  }

  getPurchaseOrders() {
    this.supplierService.getPurchaseOrders().subscribe(orders => {
      console.log(orders)
      this.orders = orders
    })
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
