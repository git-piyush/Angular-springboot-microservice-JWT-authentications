import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-sendotptoemail',
  standalone: false,
  
  templateUrl: './sendotptoemail.component.html',
  styleUrl: './sendotptoemail.component.scss'
})
export class SendotptoemailComponent {
  loading : boolean = false;
  otpForm: FormGroup;
  private snackbar = inject(MatSnackBar);
  constructor(private fb: FormBuilder,
    private service: AuthService,
    private router: Router) {
    this.otpForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit() {
    this.loading = true;
    this.service.sendOtp(this.otpForm.value).subscribe(
      (res) => {
        this.loading = false;
        this.snackbar.open(res.msg,"Close", { duration: 5000 });
        this.router.navigateByUrl("/resetpasswordpage");
      }, (err:HttpErrorResponse) => {
        this.snackbar.open(err.error,"Close", { duration: 5000 });
     }
    )
  }
}
