import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {CreatePrComponent} from "./content/site_manager/create-pr/create-pr.component";
import {ViewPrComponent} from "./content/site_manager/view-pr/view-pr.component";
import {ManageMaterialComponent} from "./content/site_manager/manage-material/manage-material.component";
import {ViewPrDetailsComponent} from "./content/site_manager/view-pr-details/view-pr-details.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: "login",
    component: LoginComponent
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
  // {
  //   path: 'folder/:id',
  //   loadChildren: () => import('./folder/folder.module').then( m => m.FolderPageModule)
  // }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
