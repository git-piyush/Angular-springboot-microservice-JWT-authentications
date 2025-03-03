import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { adminGuard } from '../../auth/guard/admin-guard/admin.guard';
import { StudentListComponent } from './admin-component/student-list/student-list.component';
import { TeacherListComponent } from './admin-component/teacher-list/teacher-list.component';
import { TeacherDetailsComponent } from './admin-component/teacher-details/teacher-details.component';
import { RegistereduserListComponent } from './admin-component/registereduser-list/registereduser-list.component';
import { UserDetailsComponent } from './admin-component/user-details/user-details.component';
import { RefcodeSettingComponent } from './admin-component/refcode-setting/refcode-setting.component';
import { HomeComponent } from '../../common-component/home-component/home/home.component';
import { CreateVacancyComponent } from './admin-component/create-vacancy/create-vacancy.component';

const routes: Routes = [
  { path:'studentlist', canActivate:[adminGuard] ,component:StudentListComponent },
  { path:'create-vacancy', canActivate:[adminGuard], component:CreateVacancyComponent},
  { path:'teacherlist', canActivate:[adminGuard],component:TeacherListComponent},
  { path:'teacherdetails/:teacherId', canActivate:[adminGuard],component:TeacherDetailsComponent},
  { path:'user-list', canActivate:[adminGuard],component:RegistereduserListComponent},
  { path:'userdetails/:userId', canActivate:[adminGuard],component: UserDetailsComponent},
  { path:'refcodesetting', canActivate:[adminGuard],component: RefcodeSettingComponent},
  { path:'deleterefcode/:refCode', canActivate:[adminGuard],component: RefcodeSettingComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
