import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-resetpassword',
  standalone: false,
  
  templateUrl: './resetpassword.component.html',
  styleUrl: './resetpassword.component.scss'
})
export class ResetpasswordComponent {
  loading : boolean = false;
  resetForm: FormGroup;
  private snackbar = inject(MatSnackBar);
  constructor(private fb: FormBuilder,
    private service: AuthService,
    private router: Router) {
    this.resetForm = this.fb.group({
      otp: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  // Custom validator to check if passwords match
  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  onSubmit() {
    this.loading = true;
    this.service.resetPassword(this.resetForm.value).subscribe(
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
