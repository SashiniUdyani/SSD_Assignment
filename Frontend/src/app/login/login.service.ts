// @ts-nocheck
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {

  }

  login(user): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/user/login', user);
  }
}
