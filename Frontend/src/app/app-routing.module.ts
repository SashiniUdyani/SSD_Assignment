import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PurchaseOrdersComponent} from "./content/supplier/purchase-orders/purchase-orders.component";
import {PurchaseOrderDetailsComponent} from "./content/supplier/purchase-order-details/purchase-order-details.component";
import {SupplierOrdersComponent} from "./content/warehouse_manager/supplier-orders/supplier-orders.component";
import {SupplierOrderDetailsComponent} from "./content/warehouse_manager/supplier-order-details/supplier-order-details.component";
import {InvoiceComponent} from "./content/supplier/invoice/invoice.component";
import {EditPurchaseOrderComponent} from "./content/procument_officer/edit-purchase-order/edit-purchase-order.component";
import {EditPurchaseOrderDetailsComponent} from "./content/procument_officer/edit-purchase-order-details/edit-purchase-order-details.component";
import {ManageMaterialComponent} from "./content/site_manager/manage-material/manage-material.component";
import {CreatePrComponent} from "./content/site_manager/create-pr/create-pr.component";
import {ViewPrComponent} from "./content/site_manager/view-pr/view-pr.component";
import {LoginComponent} from "./login/login.component";
import {ViewPrDetailsComponent} from "./content/site_manager/view-pr-details/view-pr-details.component";
import {SendQuotationsComponent} from "./content/procument_officer/send-quotations/send-quotations.component";
import {ViewQuotationsPoComponent} from "./content/procument_officer/view_quotation/view-quotations-po/view-quotations-po.component";
import {ViewQuotationDetailsPoComponent} from "./content/procument_officer/view_quotation/view-quotation-details-po/view-quotation-details-po.component";
import {ViewQuotationSuppliersComponent} from "./content/procument_officer/view_quotation/view-quotation-suppliers/view-quotation-suppliers.component";
import {ViewQuotationSComponent} from "./content/supplier/view-quotation-s/view-quotation-s.component";
import {ViewQuotationDetailsSComponent} from "./content/supplier/view-quotation-details-s/view-quotation-details-s.component";

const routes: Routes = [
  {
    path: "",
    // redirectTo: "supplier_orders",
    // redirectTo: "purchase_orders",
    redirectTo: "login",
    pathMatch: "full"
  },
  {
    path: "login",
    component: LoginComponent
  },

  {
    path: "purchase_orders",
    component: PurchaseOrdersComponent
  },
  {
    path: "purchase_order_details",
    component: PurchaseOrderDetailsComponent
  },
  {
    path: "view_invoice",
    component: InvoiceComponent
  },
  {
    path: "view_quotations_s",
    component: ViewQuotationSComponent
  },
  {
    path: "view_quotation_details_s",
    component: ViewQuotationDetailsSComponent
  },

  {
    path: "supplier_orders",
    component: SupplierOrdersComponent
  },
  {
    path: "supplier_order_details",
    component: SupplierOrderDetailsComponent
  },

  {
    path: "edit_purchase_orders",
    component: EditPurchaseOrderComponent
  },
  {
    path: "edit_purchase_order_details",
    component: EditPurchaseOrderDetailsComponent
  },
  {
    path: "send_quotations",
    component: SendQuotationsComponent
  },
  {
    path: "view_quotations_po",
    component: ViewQuotationsPoComponent
  },
  {
    path: "view_quotation_details_po",
    component: ViewQuotationDetailsPoComponent
  },
  {
    path: "view_quotation_suppliers_po",
    component: ViewQuotationSuppliersComponent
  },

  {
    path: "manage_material",
    component: ManageMaterialComponent
  },
  {
    path: "create_pr",
    component: CreatePrComponent
  },
  {
    path: "view_pr",
    component: ViewPrComponent
  },
  {
    path: "view_pr_details",
    component: ViewPrDetailsComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
