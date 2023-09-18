import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ScrollingModule} from "@angular/cdk/scrolling";
import {NotifierModule, NotifierOptions} from "angular-notifier";
import {DatePipe} from "@angular/common";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {ContentComponent} from './content/content.component';
import {NavbarComponent} from './navbar/navbar.component';
import {PurchaseOrderDetailsComponent} from './content/supplier/purchase-order-details/purchase-order-details.component';
import {SupplierOrderDetailsComponent} from './content/warehouse_manager/supplier-order-details/supplier-order-details.component';
import {SupplierOrdersComponent} from './content/warehouse_manager/supplier-orders/supplier-orders.component';
import {PurchaseOrdersComponent} from './content/supplier/purchase-orders/purchase-orders.component';
import {InvoiceComponent} from './content/supplier/invoice/invoice.component';
import {AlertBoxComponent} from './alert-box/alert-box.component';
import {EditPurchaseOrderComponent} from './content/procument_officer/edit-purchase-order/edit-purchase-order.component';
import {EditPurchaseOrderDetailsComponent} from './content/procument_officer/edit-purchase-order-details/edit-purchase-order-details.component';
import {ManageMaterialComponent} from './content/site_manager/manage-material/manage-material.component';
import { CreatePrComponent } from './content/site_manager/create-pr/create-pr.component';
import { ViewPrComponent } from './content/site_manager/view-pr/view-pr.component';
import { LoginComponent } from './login/login.component';
import { ViewPrDetailsComponent } from './content/site_manager/view-pr-details/view-pr-details.component';
import { SendQuotationsComponent } from './content/procument_officer/send-quotations/send-quotations.component';
import { ViewQuotationsPoComponent } from './content/procument_officer/view_quotation/view-quotations-po/view-quotations-po.component';
import { ViewQuotationDetailsPoComponent } from './content/procument_officer/view_quotation/view-quotation-details-po/view-quotation-details-po.component';
import { ViewQuotationSuppliersComponent } from './content/procument_officer/view_quotation/view-quotation-suppliers/view-quotation-suppliers.component';
import { ViewQuotationSComponent } from './content/supplier/view-quotation-s/view-quotation-s.component';
import { ViewQuotationDetailsSComponent } from './content/supplier/view-quotation-details-s/view-quotation-details-s.component';

const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: "middle",
      distance: 5
    },
    vertical: {
      position: "top",
      distance: 10,
      gap: 10
    }
  },
  theme: "material",
  behaviour: {
    autoHide: 5000,
    onClick: false,
    onMouseover: "pauseAutoHide",
    showDismissButton: false,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: "slide",
      speed: 300,
      easing: "ease"
    },
    hide: {
      preset: "fade",
      speed: 300,
      easing: "ease",
      offset: 50
    },
    shift: {
      speed: 300,
      easing: "ease"
    },
    overlap: 150
  }
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContentComponent,
    NavbarComponent,
    PurchaseOrderDetailsComponent,
    SupplierOrderDetailsComponent,
    SupplierOrdersComponent,
    PurchaseOrdersComponent,
    InvoiceComponent,
    AlertBoxComponent,
    EditPurchaseOrderComponent,
    EditPurchaseOrderDetailsComponent,
    ManageMaterialComponent,
    CreatePrComponent,
    ViewPrComponent,
    LoginComponent,
    ViewPrDetailsComponent,
    SendQuotationsComponent,
    ViewQuotationsPoComponent,
    ViewQuotationDetailsPoComponent,
    ViewQuotationSuppliersComponent,
    ViewQuotationSComponent,
    ViewQuotationDetailsSComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ScrollingModule,
    NotifierModule.withConfig(customNotifierOptions)
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
