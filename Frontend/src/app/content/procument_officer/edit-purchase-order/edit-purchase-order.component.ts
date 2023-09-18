// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SupplierService} from "../../../_service/supplier.service";
import {AlertBoxService} from "../../../alert-box/alert-box.service";
import {WarehouseService} from "../../../_service/warehouse.service";
import {SiteManagerService} from "../../../_service/site-manager.service";

@Component({
  selector: 'app-edit-purchase-order',
  templateUrl: './edit-purchase-order.component.html',
  styleUrls: ['./edit-purchase-order.component.css']
})
export class EditPurchaseOrderComponent implements OnInit {

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

  constructor(private router: Router, private wareHouseService: WarehouseService, private siteManagerService: SiteManagerService) {
  }

  ngOnInit(): void {
    // this.getFinalizedSupplierOrders()
    this.getPRs()
  }

  reOrderPumps() {

  }

  reOrderConsumes() {

  }

  viewSO(order) {
    this.wareHouseService.order = order
    this.router.navigate(['/edit_purchase_order_details'])
  }

  getFinalizedSupplierOrders() {
    this.wareHouseService.getFinalizedSupplierOrders().subscribe(orders => {
      console.log(orders)
      this.orders = orders
    })
  }

  getPRs() {
    this.siteManagerService.getPRs().subscribe(prs => {
      console.log(prs)
      this.orders = prs
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
}
