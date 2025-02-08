import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-teacher-list',
  standalone: false,
  
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.scss'
})
export class TeacherListComponent {
  teacherListFilterForm: FormGroup | undefined;
  teachers: any;
  statusSelector : boolean = false;
  filterSelector : boolean = true;

  isLoading:boolean=false;

  constructor(
    private service : AdminService,
    private snackbar : MatSnackBar,
    private fb : FormBuilder
  ){}

  ngOnInit(){
    this.teacherListFilterForm = this.fb.group({
      filterType:['', Validators.required],
      statusSubfilter:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })
    this.filter();
    this.getAllTeachers();
  }

  filter(){
    this.filterSelector = true;
  }

  getAllTeachers(){
    this.isLoading = true;
    this.service.getAllTeachers().subscribe((res)=>{
      this.teachers = res;
      this.isLoading
    })
    this.isLoading = false;
  }

  myFunction(val: Event) {
      if((val.target as HTMLInputElement).value === 'status'){
          this.statusSelector = true;
      }else{
        this.statusSelector = false;
      }
    }
  
  teacherListByFilter(){
    this.service.teacherListByFilter(this.teacherListFilterForm.value).subscribe((res)=>{
      this.isLoading = true;
        console.log(res);
        if(res!=null){
          this.teachers = res;
          this.snackbar.open("Teachers retrieved successfully.", "Close",{duration:5000});
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Either Email or Password is wrong.","Close", { duration: 5000 });
        this.isLoading = false;
      }
      if(err.status==404){
        this.teachers = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
      }
   })
    this.isLoading = false;
  }


}
