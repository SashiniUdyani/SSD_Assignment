// @ts-nocheck
import { Component, OnInit } from '@angular/core';
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {NavbarService} from "../navbar/navbar.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user;

  constructor(private loginS: LoginService, private router: Router, private navBarS: NavbarService) {
    this.user = this.newUser();
  }

  ngOnInit(): void {
  }

  login() {
    this.loginS.login(this.user).subscribe(user => {
      if (user !== null) {
        localStorage.setItem('user', JSON.stringify(user));
        if (user.userType === 'procument_officer') {
          this.router.navigate(['/edit_purchase_orders']);
        } else if (user.userType === 'site_manager') {
          this.router.navigate(['/view_pr']);
        } else if (user.userType === 'supplier') {
          this.router.navigate(['/purchase_orders']);
        } else if (user.userType === 'warehouse_manager') {
          this.router.navigate(['/supplier_orders']);
        }
        this.navBarS.navBar.next();
      } else {

      }
    });
  }

  newUser() {
    return {
      email: '',
      password: ''
    };
  }

}
