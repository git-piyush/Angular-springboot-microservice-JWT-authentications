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
  studentListFilterForm: FormGroup | undefined;
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
  ){
    this.Math = Math;
  }

  ngOnInit(){
    this.studentListFilterForm = this.fb.group({
      filterType:['', Validators.required],
      statusSubfilter:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })

    this.getAllStudents(this.currentPage,this.pageSize);
  }

  myFunction(val: Event) {
    if((val.target as HTMLInputElement).value === 'status'){
        this.statusSelector = true;
    }else{
      this.statusSelector = false;
    }
  }

  isSearchFilterFormEmpty():any{
    if(this.studentListFilterForm.value.filterType==null
        && this.studentListFilterForm.value.statusSubfilter==null
          && this.studentListFilterForm.value.filterText==null
    ){
      return true;
    }
  }

  studentListByFilter(){
    this.isLoading = true;
    if(!this.isSearchFormValid()){
      this.isLoading = false;
      return;
    }
    if(this.isSearchFilterFormEmpty()){
      this.getAllStudents(this.currentPage,this.pageSize);
      return;
    }
    this.currentPage = 0;
    this.service.studentListByFilter(this.currentPage,this.pageSize,this.studentListFilterForm.value.filterType,this.studentListFilterForm.value.statusSubfilter,this.studentListFilterForm.value.filterText).subscribe((res)=>{
        console.log(res);
        if(res!=null){
          this.students = res.content;
          this.totalElements = res.totalElements;
          this.pageSize = res.pageSize;
          this.isLoading = false;
          this.snackbar.open("Student retrieved successfully.", "Close",{duration:5000});
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Some Error Occured.","Close", { duration: 5000 });
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
    if((this.studentListFilterForm.value.filterType != null && 
      this.studentListFilterForm.value.filterText == null) || (this.studentListFilterForm.value.filterType != '' && 
        this.studentListFilterForm.value.filterText == '')){
        alert('Please add search text.');
        return false;
    }
    if(this.studentListFilterForm.value.filterType=='status'){
        if(this.studentListFilterForm.value.statusSubfilter == null || 
          this.studentListFilterForm.value.statusSubfilter == ''
        ){
          alert('Please add status sub filter.');
            return false;
        }
    }
    return true;
  }

  getAllStudents(pageNo:number, pageSize:number){
    this.isLoading = true;
    this.service.getAllStudents(pageNo, pageSize).subscribe((res)=>{
      this.students = res.content;
      this.totalElements = res.totalElements;
      this.pageSize = res.pageSize;
    })
    this.isLoading = false;
  }

  deleteStudent(studentId: number){
    if(confirm("Are you sure about this deletion?")){
      this.service.deleteStudent(studentId).subscribe((res)=>{
        console.log("pk"+res);
        //this.getAllStudents(pageNo:number, pageSize:number);
        this.snackbar.open("Student deleted successfully","Close",{duration:5000})
      })
    }
  }

  onPageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.currentPage = 0;
    this.getAllStudents(this.currentPage,this.pageSize);
  }

  goToPreviousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getAllStudents(this.currentPage,this.pageSize);
    }
  }

  goToNextPage(): void {
    const totalPages = Math.ceil(this.totalElements / this.pageSize);
    if (this.currentPage < totalPages - 1) {
      this.currentPage++;
      this.getAllStudents(this.currentPage,this.pageSize);
    }
  }

}
