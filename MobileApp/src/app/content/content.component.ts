import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss'],
})
export class ContentComponent implements OnInit {

  isModalTableDetails = {
    text: '',
    openTable: false,
    foundLetter: ''
  };

  reply = {
    msg: ''
  };

  constructor() {

  }

  ngOnInit(): void {
  }

  isTrueOrFalseDetails(reply:any) {
    this.isModalTableDetails.openTable = reply;
  }

  getUser() {
    return JSON.parse(localStorage.getItem('user'))
  }

}
