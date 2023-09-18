// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {WarehouseService} from "../../../_service/warehouse.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-supplier-order-details',
  templateUrl: './supplier-order-details.component.html',
  styleUrls: ['./supplier-order-details.component.css']
})
export class SupplierOrderDetailsComponent implements OnInit {

  orders = [];
  consumption = 0;
  consumed = 0;
  trip;
  fuelConsumptions = []
  selectedOrderBy1 = ''
  selectedOrderBy2 = ''
  rates2 = [];
  rates1 = [0, 0, 0, 0, 0];
  rating = 5

  alertBox = {
    alert: false,
    msg: '',
    value: ''
  };

  order
  orderDetails = []
  total = 0

  constructor(private wareHouseService: WarehouseService, private router: Router) {
  }

  ngOnInit(): void {
    this.getSupplierOrderDetails()
    this.calcTotal()
  }

  reOrderPumps() {

  }

  reOrderConsumes() {

  }

  getSupplierOrderDetails() {
    this.order = this.wareHouseService.order
    this.orderDetails = this.wareHouseService.order.purchaseOrderDetailList
    // for (let orderDetail of this.orderDetails) {
    //   orderDetail.r_unitPrice = orderDetail.unitPrice
    //   orderDetail.r_quantity = orderDetail.quantity
    // }
  }

  calcTotal() {
    this.total = 0
    for (let orderDetail of this.orderDetails) {
      this.total += (orderDetail.soUnitPrice * orderDetail.soQuantity)
    }
    // this.wareHouseService.order.poTotal = this.total
  }

  finalizeSupplierOrder() {
    this.wareHouseService.finalizeSupplierOrder(this.order.id, this.rating).subscribe(() => {
      this.router.navigate(['/supplier_orders'])
    })
  }

  makeRate1(index) {
    this.rates1 = [];
    this.rates2 = []
    for (let i = 0; i < index + 1; i++) {
      this.rates1.push(0)
    }
    for (let i = 0; i < 5 - index - 1; i++) {
      this.rates2.push(0)
    }
    this.rating = this.rates1.length
  }

  makeRate2(index) {
    // this.rates1 = [];
    // this.rates2 = []
    // for (let i = 0; i < 4 + index; i++) {
    //   this.rates1.push(0)
    // }
    // for (let i = 0; i < index; i++) {
    //   this.rates2.push(0)
    // }
    // this.rating = this.rates1.length
  }
}
