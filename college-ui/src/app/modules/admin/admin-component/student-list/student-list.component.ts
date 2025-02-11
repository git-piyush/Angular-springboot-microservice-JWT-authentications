import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-student-list',
  standalone: false,
  
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent {
  teacherListFilterForm: FormGroup | undefined;
  students: any;
  statusSelector : boolean = false;
  filterSelector : boolean = true;
  totalElements = 0;
  pageSize = 3;
  currentPage = 0;
  pageSizeOptions = [3, 6, 12];

  isLoading:boolean=false;
  Math: any;

  constructor(
    private service : AdminService,
    private snackbar : MatSnackBar,
    private fb : FormBuilder,
    private dialog: MatDialog
  ){}

  ngOnInit(){

    this.teacherListFilterForm = this.fb.group({
      filterType:['', Validators.required],
      statusSubfilter:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })

    this.getAllStudents();
  }

  myFunction(val: Event) {
    if((val.target as HTMLInputElement).value === 'status'){
        this.statusSelector = true;
    }else{
      this.statusSelector = false;
    }
  }

  studentListByFilter(){
    if(!this.isSearchFormValid()){
      return;
    }
    this.currentPage = 0;
    this.service.teacherListByFilter(this.currentPage,this.pageSize,this.teacherListFilterForm.value).subscribe((res)=>{
      this.isLoading = true;
        console.log(res);
        if(res!=null){
          this.students = res.content;
          this.totalElements = res.totalElements;
          this.pageSize = res.pageSize;
          this.isLoading = false;
          this.snackbar.open("Teachers retrieved successfully.", "Close",{duration:5000});
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Either Email or Password is wrong.","Close", { duration: 5000 });
        this.isLoading = false;
      }
      if(err.status==404){
        this.students = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
      }
   })
    this.isLoading = false;
  }

  isSearchFormValid():any{
    if((this.teacherListFilterForm.value.filterType != null && 
      this.teacherListFilterForm.value.filterText == null) || (this.teacherListFilterForm.value.filterType != '' && 
        this.teacherListFilterForm.value.filterText == '')){
        alert('Please add search text.');
        return false;
    }
    if(this.teacherListFilterForm.value.filterType=='status'){
        if(this.teacherListFilterForm.value.statusSubfilter == null || 
          this.teacherListFilterForm.value.statusSubfilter == ''
        ){
          alert('Please add status sub filter.');
            return false;
        }
    }
    return true;
  }

  getAllStudents(){
    this.service.getAllStudents().subscribe((res)=>{
      this.students = res.content;
    })
  }

  deleteStudent(studentId: number){
    if(confirm("Are you sure about this deletion?")){
      this.service.deleteStudent(studentId).subscribe((res)=>{
        console.log("pk"+res);
        this.getAllStudents();
        this.snackbar.open("Student deleted successfully","Close",{duration:5000})
      })
    }
  }

}
