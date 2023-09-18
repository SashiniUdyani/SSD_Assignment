import {Component, OnInit} from '@angular/core';
import {SiteManagerService} from "../site-manager.service";
import {ProcumentOfficerService} from "../procument-officer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-view-pr-details',
  templateUrl: './view-pr-details.component.html',
  styleUrls: ['./view-pr-details.component.scss'],
})
export class ViewPrDetailsComponent implements OnInit {

  order;
  orderDetails = []
  total = 0
  item
  isModalTableDetails = {
    text: '',
    openTable: false,
    foundLetter: ''
  };

  constructor(private siteManagerService: SiteManagerService, private procumentOfficerService: ProcumentOfficerService, private router: Router) {
    this.item = this.procumentOfficerService.newItem()
  }

  ngOnInit(): void {
    this.getSupplierOrderDetails()
    this.calcTotal()
  }

  getSupplierOrderDetails() {
    this.order = this.siteManagerService.order
    this.orderDetails = this.siteManagerService.order.purchaseOrderDetailList
    // for (let orderDetail of this.orderDetails) {
    //   orderDetail.r_unitPrice = orderDetail.unitPrice
    //   orderDetail.r_quantity = orderDetail.quantity
    // }
  }

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

  getItemById() {
    this.siteManagerService.getItemById(this.item.id).subscribe(item => {
      this.item = item
    })
  }

  addItem() {
    let poDetail = {
      id: 'PD' + this.order.id + this.item.id,
      material: {
        id: this.item.id
      },
      poUnitPrice: this.item.poUnitPrice,
      poQuantity: this.item.poQuantity,
      purchaseOrder: {
        id: this.order.id
      }
    }
    this.procumentOfficerService.updatePR(poDetail).subscribe((item) => {
      this.orderDetails.push({
        id: item.id,
        material: {
          id: this.item.id,
          itemName: this.item.itemName,
          itemType: this.item.itemType
        },
        poUnitPrice: this.item.poUnitPrice,
        poQuantity: this.item.poQuantity
      })
      this.calcTotal()
    })
    this.isTrueOrFalseDetails(false)
  }

  removePO() {
    this.procumentOfficerService.removePurchaseOrder(this.order.id).subscribe(() => {
      this.router.navigate(['/view_pr'])
    })
  }

}
