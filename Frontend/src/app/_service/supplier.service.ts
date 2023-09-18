// @ts-nocheck
import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  order

  constructor(private http: HttpClient) {
  }

  // addCustomer(customer): Observable<any> {
  //   return this.http.post<any>(environment.backend_url + '/customer/addCustomer', customer);
  // }
  //
  finalizePurchaseOrder(order): Observable<any> {
    return this.http.put<any>(environment.backend_url + '/supplier/finalizePurchaseOrder/' + order.id, order);
  }

  finalizeQuotation(order): Observable<any> {
    return this.http.put<any>(environment.backend_url + '/supplier/finalizeQuotation/' + JSON.parse(localStorage.getItem('user')).id, order);
  }

  acceptOrder(orderId) {
    return this.http.get<any>(environment.backend_url + '/supplier/acceptOrder/' + orderId);
  }

  //
  // deleteCustomer(id): Observable<any> {
  //   return this.http.delete<any>(environment.backend_url + '/customer/deleteCustomer/' + id);
  // }
  //
  // getCustomer(email, contactNumber): Observable<any> {
  //   return this.http.get<any>(environment.backend_url + '/customer/getCustomer/' + email + '/' + contactNumber);
  // }

  getPurchaseOrders(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/supplier/getPurchaseOrders/' + JSON.parse(localStorage.getItem('user')).id);
  }

  getQuotations(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/supplier/getQuotations/' + JSON.parse(localStorage.getItem('user')).id);
  }

  getFinalizedPurchaseOrders(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/supplier/getFinalizedPurchaseOrders/' + JSON.parse(localStorage.getItem('user')).id);
  }

  deliverOrder(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/supplier/deliverOrder/' + id);
  }

  newPurchaseOrder() {
    return {
      accepted: false,
      finalized: false
    }
  }
}
