import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { AdminService } from '../../admin-service/admin.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { VacancyService } from '../../admin-service/vacancy-service/vacancy.service';
import { SettingService } from '../../admin-service/setting-service/setting.service';

@Component({
  selector: 'app-create-vacancy',
  standalone: false,
  
  templateUrl: './create-vacancy.component.html',
  styleUrl: './create-vacancy.component.scss'
})
export class CreateVacancyComponent {
  loading : boolean = false;
  vacancyForm: FormGroup;
  refUserTypeList:any;
  branchList:any;
  private snackbar = inject(MatSnackBar);
  teacherVacancy: boolean;
  constructor(private fb: FormBuilder,private settingService: SettingService,
    private service: VacancyService,
    private router: Router) {
      this.vacancyForm = this.fb.group({
        applicantType:['', Validators.required],
        branch: ['', [Validators.required, Validators.minLength(2)]],
        designations: ['', Validators.required],
        specialization: ['', Validators.required],
        startDate:['', Validators.required],
        endDate:['', Validators.required],
        noOfVacancy:['', Validators.required]
      });
  }
  ngOnInit(){
    this.getUserTypeDropDown("USER_TYPE");
    this.getEngBranchDropDown("ENG_BRANCH");
  }

  getUserTypeDropDown(userType:string){
    this.settingService.getUserTypeDropDown(userType).subscribe((res)=>{
      console.log(res);
      this.refUserTypeList = res;
    })
  }

  getEngBranchDropDown(engType:string){
    this.settingService.getUserTypeDropDown(engType).subscribe((res)=>{
      this.branchList = res;
    })
  }

  createVacancy() {
    this.loading = true;
    console.log(this.vacancyForm.value);
    this.service.createVacancy(this.vacancyForm.value).subscribe(
      (res) => {
        this.loading = false;
        window.location.reload();
        this.snackbar.open(res.msg,"Close", { duration: 5000 });
        
      }, (err:HttpErrorResponse) => {
        this.snackbar.open(err.error.msg,"Close", { duration: 5000 });
     }
    )
  }

  onUserTypeChange(val: Event) {
    if((val.target as HTMLInputElement).value === 'RCTEC'){
        this.teacherVacancy = true;
    }else{
      this.teacherVacancy = false;
      this.vacancyForm.controls["designations"].setErrors(null);
      this.vacancyForm.controls["specialization"].setErrors(null);
    }
  }
}

