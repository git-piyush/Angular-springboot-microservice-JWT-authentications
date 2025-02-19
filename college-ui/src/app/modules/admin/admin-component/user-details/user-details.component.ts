import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-details',
  standalone: false,
  
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.scss'
})
export class UserDetailsComponent {

  userId:number;
  isLoading:boolean;
  user:any;

  constructor(
    private service: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router : Router
  ){
    this.userId = this.activatedRoute.snapshot.params['userId'];
  }
  

  ngOnInit(){
    this.getRegisteredUserDetailsById()
  }

  getRegisteredUserDetailsById(){
    this.service.getRegisteredUserDetailsById(this.userId).subscribe((res)=>{
      this.isLoading = true;
        console.log(res);
        if(res!=null){
          this.user = res;
          this.isLoading = false;
          this.snackBar.open("Details retrieved successfully.", "Close",{duration:5000});
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackBar.open("Some Error Occured.","Close", { duration: 5000 });
        this.isLoading = false;
      }
      if(err.status==404){
        this.user = null;
        console.log(err);
        this.snackBar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
      }
   })
  }

}
