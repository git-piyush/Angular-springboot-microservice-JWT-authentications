import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { appGuard } from './guard/app.guard';
import { HomeComponent } from './common-component/home-component/home/home.component';
import { ProfileComponent } from './common-component/prfile-component/profile/profile.component';
import { LoginComponent } from './common-component/login-component/login/login.component';
import { RegisterComponent } from './common-component/register-component/register/register.component';

const routes: Routes = [
  {path:"home", component:HomeComponent},
  {path:"login", component:LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"viewprofile", canActivate:[appGuard], component:ProfileComponent},
  { path:"admin", loadChildren: () => import("./modules/admin/admin.module").then(m=> m.AdminModule) },
  { path:"admindashboard", loadChildren: () => import("./modules/admin/admin.module").then(m=> m.AdminModule) },
  { path:"studentlist", loadChildren: () => import("./modules/admin/admin.module").then(m=> m.AdminModule) },
  { path:"addstudent", loadChildren: () => import("./modules/admin/admin.module").then(m=> m.AdminModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
