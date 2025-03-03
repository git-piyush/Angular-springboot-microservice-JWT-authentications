import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
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
import { UserDetailsComponent } from './admin-component/user-details/user-details.component';
import { RefcodeSettingComponent } from './admin-component/refcode-setting/refcode-setting.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { CreateVacancyComponent } from './admin-component/create-vacancy/create-vacancy.component';
import { MatTooltipModule } from '@angular/material/tooltip';

@NgModule({
  declarations: [
    StudentListComponent,
    TeacherListComponent,
    TeacherDetailsComponent,
    RegistereduserListComponent,
    UserDetailsComponent,
    RefcodeSettingComponent,
    CreateVacancyComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    //
    MatTooltipModule,
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
    MatProgressBarModule,
    MatGridListModule
  ],
  providers:[],
  bootstrap:[AppComponent]
})
export class AdminModule { }
