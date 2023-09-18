import { Component, OnInit } from '@angular/core';
import {WarehouseService} from "../warehouse.service";
import {SiteManagerService} from "../site-manager.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-pr',
  templateUrl: './create-pr.component.html',
  styleUrls: ['./create-pr.component.scss'],
})
export class CreatePrComponent implements OnInit {

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

  isModalTableDetails = {
    text: '',
    openTable: false,
    foundLetter: ''
  };

  order
  orderDetails = []
  total = 0

  constructor(private wareHouseService: WarehouseService, private siteManagerService: SiteManagerService, private router: Router) {
    this.item = this.siteManagerService.newItem()
    this.order = this.siteManagerService.newOrder()
  }

  ngOnInit(): void {
    // this.getSupplierOrderDetails()
    this.calcTotal()
  }

  // reOrderPumps() {
  //
  // }
  //
  // reOrderConsumes() {
  //
  // }

  // getSupplierOrderDetails() {
  //   this.order = this.wareHouseService.order
  //   this.orderDetails = this.wareHouseService.order.purchaseOrderDetailList
  //   // for (let orderDetail of this.orderDetails) {
  //   //   orderDetail.r_unitPrice = orderDetail.unitPrice
  //   //   orderDetail.r_quantity = orderDetail.quantity
  //   // }
  // }

  calcTotal() {
    this.total = 0
    for (let orderDetail of this.orderDetails) {
      this.total += (orderDetail.poUnitPrice * orderDetail.poQuantity)
    }
    // this.wareHouseService.order.poTotal = this.total
  }

  isTrueOrFalseDetails(reply) {
    this.isModalTableDetails.openTable = reply;
  }

  // isTrueOrFalseDetailsRej(reply) {
  //   this.isModalTableDetailsRej.openTable = reply;
  // }

  item

  addItem() {
    let item = JSON.parse(JSON.stringify(this.item))
    this.orderDetails.push({
      id: item.id,
      material: {
        id: item.id,
        itemName: item.itemName,
        itemType: item.itemType
      },
      poUnitPrice: item.poUnitPrice,
      poQuantity: item.poQuantity
    })
    this.calcTotal()
    this.isTrueOrFalseDetails(false)
  }

  getItemById() {
    this.siteManagerService.getItemById(this.item.id).subscribe(item => {
      this.item = item
    })
  }

  addPR() {
    this.order.purchaseOrderDetails = this.orderDetails
    this.order.siteManager = {
      id: JSON.parse(localStorage.getItem('user')).id
    }
    this.siteManagerService.addPR(this.order).subscribe(() => {
      this.router.navigate(['/view_pr'])
    })
  }

  removeItem(index) {
    this.orderDetails.splice(index, 1)
  }
}
