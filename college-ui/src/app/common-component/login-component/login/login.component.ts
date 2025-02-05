import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { StorageService } from '../../../auth/services/storage/storage.service';

@Component({
  selector: 'app-login',
  standalone: false,
  
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup | undefined;
  loading : boolean = false;
  

  constructor(
    private service : AuthService,
    private fb : FormBuilder,
    private router : Router,
    private snackbar: MatSnackBar
  ){}

  ngOnInit(){
    this.loginForm = this.fb.group({
      email:['', Validators.required],
      password:['',Validators.required]
    })
  }

  login(){
    this.loading = true;
    this.service.login(
      this.loginForm.get(['email'])!.value,
      this.loginForm.get(['password'])!.value,
      ).subscribe((res)=>{
        this.loading = false;
      if(StorageService.isAdminLoggedIn()){
        console.log("Admin");
        this.router.navigateByUrl("/admin/admindashboard");
      }else if(StorageService.isStudentLoggedIn()){
        console.log("Student");
        this.router.navigateByUrl("/student/studentdashboard");
      }
      console.log("Nothing");
    }),
    error => {
      if(error.status==406){
        this.snackbar.open("user is not active","Close",{
          duration: 5000
        });
        
      }else{
        this.snackbar.open("Bad credentials", "Close");
        duration:5000
      }
    }
  }
}
