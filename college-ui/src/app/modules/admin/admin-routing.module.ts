import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { adminGuard } from '../../auth/guard/admin-guard/admin.guard';
import { AdminDashboardComponent } from './admin-component/admin-dashboard/admin-dashboard.component';
import { AddStudentComponent } from './admin-component/add-student/add-student.component';
import { StudentListComponent } from './admin-component/student-list/student-list.component';

const routes: Routes = [
  { path:'addstudent', canActivate:[adminGuard] ,component:AddStudentComponent },
  { path:'studentlist', canActivate:[adminGuard] ,component:StudentListComponent },
  { path:'admindashboard', canActivate:[adminGuard], component:AdminDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
