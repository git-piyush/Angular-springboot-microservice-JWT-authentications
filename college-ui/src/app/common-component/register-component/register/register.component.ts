import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { StorageService } from '../../../auth/services/storage/storage.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: false,
  
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})

export class RegisterComponent {
  websiteList: any = ['Javatpoint.com', 'HDTuto.com', 'Tutorialandexample.com'];
  loading : boolean = false;
  registrationForm: FormGroup;
  private snackbar = inject(MatSnackBar);
  constructor(private fb: FormBuilder,
    private service: AuthService,
    private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      confirmPassword: ['', Validators.required],
      userType:['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  // Custom validator to check if passwords match
  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  onSubmit() {
    this.loading = true;
    this.service.register(this.registrationForm.value).subscribe(
      (res) => {
        this.loading = false;
        this.snackbar.open(res.msg,"Close", { duration: 5000 });
        this.router.navigateByUrl("/login");
      }, (err:HttpErrorResponse) => {
        this.snackbar.open(err.error.msg,"Close", { duration: 5000 });
     }
    )
  }
}
