// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {WarehouseService} from "../../../_service/warehouse.service";

@Component({
  selector: 'app-supplier-orders',
  templateUrl: './supplier-orders.component.html',
  styleUrls: ['./supplier-orders.component.css']
})
export class SupplierOrdersComponent implements OnInit {

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

  constructor(private router: Router, private wareHouseService: WarehouseService) {
  }

  ngOnInit(): void {
    this.getFinalizedSupplierOrders()
  }

  reOrderPumps() {

  }

  reOrderConsumes() {

  }

  viewSO(order) {
    this.wareHouseService.order = order
    this.router.navigate(['/supplier_order_details'])
  }

  getFinalizedSupplierOrders() {
    this.wareHouseService.getFinalizedSupplierOrders().subscribe(orders => {
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
}
