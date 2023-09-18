import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouteReuseStrategy} from '@angular/router';

import {IonicModule, IonicRouteStrategy} from '@ionic/angular';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {ContentComponent} from "./content/content.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {CreatePrComponent} from "./content/site_manager/create-pr/create-pr.component";
import {ManageMaterialComponent} from "./content/site_manager/manage-material/manage-material.component";
import {ViewPrComponent} from "./content/site_manager/view-pr/view-pr.component";
import {ViewPrDetailsComponent} from "./content/site_manager/view-pr-details/view-pr-details.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {LoginComponent} from "./login/login.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContentComponent,
    NavbarComponent,
    CreatePrComponent,
    ManageMaterialComponent,
    ViewPrComponent,
    ViewPrDetailsComponent,
    LoginComponent
  ],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule, HttpClientModule, FormsModule],
  providers: [{provide: RouteReuseStrategy, useClass: IonicRouteStrategy}],
  bootstrap: [AppComponent],
})
export class AppModule {
}
