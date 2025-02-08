import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { appGuard } from './guard/app.guard';
import { HomeComponent } from './common-component/home-component/home/home.component';
import { ProfileComponent } from './common-component/prfile-component/profile/profile.component';
import { LoginComponent } from './common-component/login-component/login/login.component';
import { RegisterComponent } from './common-component/register-component/register/register.component';
import { SendotptoemailComponent } from './common-component/sendotp-component/sendotptoemail/sendotptoemail.component';
import { ResetpasswordComponent } from './common-component/resetpassword-component/resetpassword/resetpassword.component';

const routes: Routes = [
  {path:"home", component:HomeComponent},
  {path:"login", component:LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"sendotp", component:SendotptoemailComponent},
  {path:"resetpasswordpage", component:ResetpasswordComponent},
  {path:"viewprofile", canActivate:[appGuard], component:ProfileComponent},
  { path:"admin", loadChildren: () => import("./modules/admin/admin.module").then(m=> m.AdminModule) }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
