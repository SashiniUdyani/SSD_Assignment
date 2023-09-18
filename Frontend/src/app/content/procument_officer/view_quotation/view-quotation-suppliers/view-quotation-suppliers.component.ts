// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {ProcumentOfficerService} from "../../../../_service/procument-officer.service";
import {WarehouseService} from "../../../../_service/warehouse.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-view-quotation-suppliers',
  templateUrl: './view-quotation-suppliers.component.html',
  styleUrls: ['./view-quotation-suppliers.component.css']
})
export class ViewQuotationSuppliersComponent implements OnInit {

  suppliers = []
  order

  constructor(private procumentOfficerService: ProcumentOfficerService, private wareHouseService: WarehouseService, private router: Router) {

  }

  ngOnInit(): void {
    this.order = this.procumentOfficerService.order
    this.getSuppliers()
  }

  getSuppliers() {
    this.procumentOfficerService.quotationSuppliers(this.order.id).subscribe((suppliers) => {
      this.suppliers = suppliers
    })
  }

  sendToSupplier(supplier) {
    this.procumentOfficerService.supplier = supplier
    this.router.navigate(['/view_quotation_details_po'])
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
