import {Component, OnInit} from '@angular/core';
import {SiteManagerService} from "../site-manager.service";
import {ProcumentOfficerService} from "../procument-officer.service";

@Component({
  selector: 'app-manage-material',
  templateUrl: './manage-material.component.html',
  styleUrls: ['./manage-material.component.scss'],
})
export class ManageMaterialComponent implements OnInit {

  items = []

  isModalTableDetails = {
    text: '',
    openTable: false,
    foundLetter: ''
  };

  constructor(private procumentOfficerService: ProcumentOfficerService, private siteManagerService: SiteManagerService) {
    this.item = this.procumentOfficerService.newItem()
  }

  ngOnInit(): void {
    this.getItems()
  }

  isTrueOrFalseDetails(reply) {
    this.isModalTableDetails.openTable = reply;
  }

  item

  addItem() {
    this.siteManagerService.addItem(this.item).subscribe(() => {
      this.items.push(JSON.parse(JSON.stringify(this.item)))
    })
    this.isTrueOrFalseDetails(false)
  }

  getItems() {
    this.siteManagerService.getItems().subscribe(items => {
      this.items = items
    })
  }

  removeItem(id, index) {
    this.siteManagerService.removeMaterial(id).subscribe(() => {
      this.items.splice(index, 1)
    })
  }
}
