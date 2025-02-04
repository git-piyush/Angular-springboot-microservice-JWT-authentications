import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-component/admin-dashboard/admin-dashboard.component';
import { AddStudentComponent } from './admin-component/add-student/add-student.component';
import { StudentListComponent } from './admin-component/student-list/student-list.component';

@NgModule({
  declarations: [
    AdminDashboardComponent,
    AddStudentComponent,
    StudentListComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
