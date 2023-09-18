// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {WarehouseService} from "../../../../_service/warehouse.service";
import {SiteManagerService} from "../../../../_service/site-manager.service";
import {ProcumentOfficerService} from "../../../../_service/procument-officer.service";

@Component({
  selector: 'app-view-quotations-po',
  templateUrl: './view-quotations-po.component.html',
  styleUrls: ['./view-quotations-po.component.css']
})
export class ViewQuotationsPoComponent implements OnInit {

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

  constructor(private router: Router, private wareHouseService: WarehouseService, private procumentOfficerService: ProcumentOfficerService) {
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
    this.procumentOfficerService.order = order
    this.router.navigate(['/view_quotation_suppliers_po'])
  }

  // getFinalizedSupplierOrders() {
  //   this.wareHouseService.getFinalizedSupplierOrders().subscribe(orders => {
  //     console.log(orders)
  //     this.orders = orders
  //   })
  // }

  getPRs() {
    this.procumentOfficerService.viewQuotations().subscribe(prs => {
      console.log(prs)
      this.orders = prs
    })
  }

  // calcTotal(order) {
  //   console.log(order)
  //   let total = 0
  //   for (let orderDetail of order.purchaseOrderDetailList) {
  //     this.total += (orderDetail.soUnitPrice * orderDetail.soQuantity)
  //   }
  //   return total;
  //   // this.wareHouseService.order.poTotal = this.total
  // }
}
