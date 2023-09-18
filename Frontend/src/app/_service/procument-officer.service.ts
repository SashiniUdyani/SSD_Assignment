// @ts-nocheck
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProcumentOfficerService {

  item
  order
  supplier

  constructor(private http: HttpClient) {
  }

  updatePR(poDetail): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/procument_officer/updatePR', poDetail);
  }

  approveOrder(orderId) {
    return this.http.get<any>(environment.backend_url + '/procument_officer/approveOrder/' + orderId);
  }

  getSuppliers() {
    return this.http.get<any>(environment.backend_url + '/procument_officer/getSuppliers');
  }

  sendToSupplier(purchaseOrder, supplier) {
    return this.http.get<any>(environment.backend_url + '/procument_officer/sendSupplier/' + purchaseOrder + '/' + supplier);
  }

  viewQuotations() {
    return this.http.get<any>(environment.backend_url + '/procument_officer/viewQuotations/' + JSON.parse(localStorage.getItem('user')).company.companyId);
  }

  quotationSuppliers(poId) {
    return this.http.get<any>(environment.backend_url + '/procument_officer/quotationSuppliers/' + poId);
  }

  quotationDetails(poId, supplierId) {
    return this.http.get<any>(environment.backend_url + '/procument_officer/quotationDetails/' + poId + '/' + supplierId);
  }

  finalizeSupplier(poId, supplierId) {
    return this.http.get<any>(environment.backend_url + '/procument_officer/finalizeSupplier/' + poId + '/' + supplierId);
  }

  removePurchaseOrder(poId) {
    return this.http.get<any>(environment.backend_url + '/procument_officer/removePurchaseOrder/' + poId);
  }

  payOrder(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/procument_officer/payOrder/'+ id);
  }

  newItem() {
    return {
      id: '',
      itemName: '',
      itemType: '',
      poUnitPrice: 0,
      poQuantity: 0
    }
  }
}
