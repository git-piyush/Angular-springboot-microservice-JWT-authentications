import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { AdminService } from '../../admin-service/admin.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin-dashboard',
  standalone: false,
  
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {

  websiteList: any = ['Javatpoint.com', 'HDTuto.com', 'Tutorialandexample.com'];
  loading : boolean = false;
  vacancyForm: FormGroup;
  private snackbar = inject(MatSnackBar);
  teacherVacancy: boolean;
  constructor(private fb: FormBuilder,
    private service: AdminService,
    private router: Router) {
      this.vacancyForm = this.fb.group({
        userType:['', Validators.required],
        branch: ['', [Validators.required, Validators.minLength(2)]],
        course: ['', Validators.required],
        designations: ['', Validators.required],
        specialization: ['', Validators.required],
        startDate:['', Validators.required],
        endDate:['', Validators.required],
        noOfVacancy:['', Validators.required]
      });
  }
  onSubmit() {
    this.loading = true;
    console.log(this.vacancyForm);
    this.service.createStudentVacancy(this.vacancyForm.value).subscribe(
      (res) => {
        this.loading = false;
        this.snackbar.open(res.msg,"Close", { duration: 5000 });
        this.router.navigateByUrl("/login");
      }, (err:HttpErrorResponse) => {
        this.snackbar.open(err.error.msg,"Close", { duration: 5000 });
     }
    )
  }

  myUserTypeChange(val: Event) {
    if((val.target as HTMLInputElement).value === 'RCTEC'){
        this.teacherVacancy = true;
    }else{
      this.teacherVacancy = false;
      this.vacancyForm.controls["designations"].setErrors(null);
      this.vacancyForm.controls["specialization"].setErrors(null);
     
    }
  }

}
