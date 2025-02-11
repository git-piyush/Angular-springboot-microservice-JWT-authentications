import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-component/admin-dashboard/admin-dashboard.component';
import { StudentListComponent } from './admin-component/student-list/student-list.component';

import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatMenuModule} from '@angular/material/menu';
import { TeacherListComponent } from './admin-component/teacher-list/teacher-list.component';
import { TeacherDetailsComponent } from './admin-component/teacher-details/teacher-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { AppComponent } from '../../app.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { RegistereduserListComponent } from './admin-component/registereduser-list/registereduser-list.component';
@NgModule({
  declarations: [
    AdminDashboardComponent,
    StudentListComponent,
    TeacherListComponent,
    TeacherDetailsComponent,
    RegistereduserListComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    //
    ReactiveFormsModule,
    FormsModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatMenuModule,
    MatDialogModule,
    MatProgressBarModule
  ],
  providers:[],
  bootstrap:[AppComponent]
})
export class AdminModule { }
