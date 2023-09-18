import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  order

  constructor(private http: HttpClient) {
  }

  getFinalizedSupplierOrders(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/warehouse/getFinalizedSupplierOrders/' + JSON.parse(localStorage.getItem('user')).company.companyId);
  }
}
