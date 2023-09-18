// @ts-nocheck
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SiteManagerService {

  order
  item

  constructor(private http: HttpClient) {
  }

  addItem(item): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/site_manager/addItem', item);
  }

  addPR(pr): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/site_manager/addPR', pr);
  }

  getItems(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/site_manager/getItems');
  }

  getPRs(): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/site_manager/getPRs');
  }

  getItemById(id): Observable<any> {
    return this.http.get<any>(environment.backend_url + '/site_manager/getItemById/' + id);
  }

  removeItem(id): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/site_manager/removeItem/' + id);
  }

  rejectPurchaseOrder(id): Observable<any> {
    return this.http.delete<any>(environment.backend_url + '/site_manager/rejectPurchaseOrder/' + id);
  }

  editInfo(order): Observable<any> {
    return this.http.post<any>(environment.backend_url + '/site_manager/editInfo', order);
  }

  newOrder() {
    return {
      id: '',
      deliverNote: '',
      handlingInstruction: '',
      note: '',
      addedAt: '',
      siteName: ''
    }
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
