import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-teacher-list',
  standalone: false,
  
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.scss'
})
export class TeacherListComponent {
  teacherListFilterForm: FormGroup | undefined;
  draftMailForm: FormGroup | undefined;
  teachers: any;
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
    
  ){
    this.Math = Math;
  }

  ngOnInit(){
    this.teacherListFilterForm = this.fb.group({
      filterType:['', Validators.required],
      statusSubfilter:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })
    this.draftMailForm = this.fb.group({
      recipientEmail:['', Validators.required],
      message:['',Validators.required],
      subject:['', Validators.required]
    })
    this.getAllTeachers(this.currentPage,this.pageSize);
  }

  getAllTeachers(pageNo:number, pageSize:number){
    this.isLoading = true;
    this.service.getAllTeachers(pageNo,pageSize).subscribe((res)=>{
      this.teachers = res.content;
      this.totalElements = res.totalElements;
      this.pageSize = res.pageSize;
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
  
  teacherListByFilter(){
    this.isLoading = true;
    if(!this.isSearchFormValid()){
      return;
    }
    this.currentPage = 0;
    this.service.teacherListByFilter(this.currentPage,this.pageSize,this.teacherListFilterForm.value).subscribe((res)=>{
        if(res!=null){
          this.teachers = res.content;
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
        this.teachers = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
      }
   })
  }

  displayStyle = "none"; 
  openPopup(email:any) {
    this.draftMailForm.value.recipientEmail=email;
    const student = this.draftMailForm.value;
    this.draftMailForm.patchValue(student);
    this.displayStyle = "block";
  }
  
  sendMail(){
    this.isLoading = true;
    console.log(this.draftMailForm.value);
    this.service.sendSimpleMail(this.draftMailForm.value).subscribe((res)=>{
      this.isLoading = true;
        if(res!=null){
          this.snackbar.open("Email Sent.", "Close",{duration:5000});
          this.closePopup();
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Either Email or Password is wrong.","Close", { duration: 5000 });
        this.isLoading = false;
        this.closePopup();
      }
      if(err.status==404){
        this.teachers = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
        this.closePopup();
      }
      if(err.status==200){
        this.teachers = null;
        console.log(err);
        this.snackbar.open('Email has been sent.',"Close", { duration: 5000 });
        this.isLoading = false;
        this.closePopup();
        this.getAllTeachers(this.currentPage,this.pageSize);
      }
   })
  }
  closePopup() {
    this.isLoading = false;
    this.draftMailForm.value.subject=null;
    this.draftMailForm.value.message=null;
    this.displayStyle = "none"; 
  }


  onPageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.currentPage = 0;
    this.getAllTeachers(this.currentPage,this.pageSize);
  }

  goToPreviousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getAllTeachers(this.currentPage,this.pageSize);
    }
  }

  goToNextPage(): void {
    const totalPages = Math.ceil(this.totalElements / this.pageSize);
    if (this.currentPage < totalPages - 1) {
      this.currentPage++;
      this.getAllTeachers(this.currentPage,this.pageSize);
    }
  }

}
