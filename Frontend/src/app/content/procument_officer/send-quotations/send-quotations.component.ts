// @ts-nocheck
import { Component, OnInit } from '@angular/core';
import {ProcumentOfficerService} from "../../../_service/procument-officer.service";
import {WarehouseService} from "../../../_service/warehouse.service";

@Component({
  selector: 'app-send-quotations',
  templateUrl: './send-quotations.component.html',
  styleUrls: ['./send-quotations.component.css']
})
export class SendQuotationsComponent implements OnInit {

  suppliers = []
  order

  constructor(private procumentOfficerService: ProcumentOfficerService, private wareHouseService: WarehouseService) {

  }

  ngOnInit(): void {
    this.getSuppliers()
    this.order = this.wareHouseService.order
  }

  getSuppliers() {
    this.procumentOfficerService.getSuppliers().subscribe((suppliers) => {
      this.suppliers = suppliers
    })
  }

  sendToSupplier(supplier) {
    this.procumentOfficerService.sendToSupplier(this.order.id, supplier.id).subscribe((reply) => {
      supplier.sent = reply
    })
  }

}
