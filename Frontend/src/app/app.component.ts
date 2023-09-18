// @ts-nocheck
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'procument-system';

  getUser() {
    return JSON.parse(localStorage.getItem('user'))
  }
}
