import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { adminGuard } from '../../auth/guard/admin-guard/admin.guard';
import { AdminDashboardComponent } from './admin-component/admin-dashboard/admin-dashboard.component';
import { StudentListComponent } from './admin-component/student-list/student-list.component';
import { TeacherListComponent } from './admin-component/teacher-list/teacher-list.component';
import { TeacherDetailsComponent } from './admin-component/teacher-details/teacher-details.component';

const routes: Routes = [
  { path:'studentlist', canActivate:[adminGuard] ,component:StudentListComponent },
  { path:'admindashboard', canActivate:[adminGuard], component:AdminDashboardComponent},
  { path:'teacherlist', canActivate:[adminGuard],component:TeacherListComponent},
  { path:'teacherdetails/:teacherId', canActivate:[adminGuard],component:TeacherDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
