// @ts-nocheck
import {Component, OnInit} from '@angular/core';
import {SiteManagerService} from "../../../_service/site-manager.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-view-pr',
  templateUrl: './view-pr.component.html',
  styleUrls: ['./view-pr.component.css']
})
export class ViewPrComponent implements OnInit {

  orders = []

  isModalTableDetails = {
    text: '',
    openTable: false,
    foundLetter: ''
  };

  constructor(private siteManagerService: SiteManagerService, private router: Router) {
  }

  ngOnInit(): void {
    this.getPRs()
  }

  isTrueOrFalseDetails(reply) {
    this.isModalTableDetails.openTable = reply;
  }

  getPRs() {
    this.siteManagerService.getPRs().subscribe(prs => {
      this.orders = prs
    })
  }

  viewPRDetails(order) {
    this.siteManagerService.order = order
    this.router.navigate(['/view_pr_details'])
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
