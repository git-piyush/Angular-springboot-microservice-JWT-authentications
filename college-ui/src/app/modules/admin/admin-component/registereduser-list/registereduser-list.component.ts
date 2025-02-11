import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-registereduser-list',
  standalone: false,
  
  templateUrl: './registereduser-list.component.html',
  styleUrl: './registereduser-list.component.scss'
})
export class RegistereduserListComponent {

  draftMailForm: FormGroup | undefined;
  userListFilterForm: FormGroup | undefined;
  users: any;
  statusSelector : boolean = false;
  filterSelector : boolean = true;
  totalElements = 0;
  pageSize = 3;
  currentPage = 0;
  pageSizeOptions = [3, 6, 12];

  isLoading:boolean=false;
  sendingmail:boolean=false;
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

    this.userListFilterForm = this.fb.group({
      filterType:['', Validators.required],
      statusSubfilter:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })

    this.draftMailForm = this.fb.group({
      recipientEmail:['', Validators.required],
      message:['',Validators.required],
      subject:['', Validators.required]
    })

    this.getAllRegisteredUser(this.currentPage,this.pageSize);
  }

  myFunction(val: Event) {
    if((val.target as HTMLInputElement).value === 'status'){
        this.statusSelector = true;
    }else{
      this.statusSelector = false;
    }
  }

  isSearchFilterFormEmpty():any{
    if(this.userListFilterForm.value.filterType==null
        && this.userListFilterForm.value.statusSubfilter==null
          && this.userListFilterForm.value.filterText==null
    ){
      return true;
    }
  }

  userListByFilter(){
    if(!this.isSearchFormValid()){
      return;
    }
    if(this.isSearchFilterFormEmpty()){
      this.getAllRegisteredUser(this.currentPage,this.pageSize);
      return;
    }
    this.currentPage = 0;
    this.service.getAllRegistredUserByFilter(this.currentPage,this.pageSize,this.userListFilterForm.value.filterType,this.userListFilterForm.value.statusSubfilter,this.userListFilterForm.value.filterText).subscribe((res)=>{
      this.isLoading = true;
        console.log(res);
        if(res!=null){
          this.users = res.content;
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
        this.users = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
      }
   })
  }

  isSearchFormValid():any{
    if((this.userListFilterForm.value.filterType != null && 
      this.userListFilterForm.value.filterText == null) || (this.userListFilterForm.value.filterType != '' && 
        this.userListFilterForm.value.filterText == '')){
        alert('Please add search text.');
        return false;
    }
    if(this.userListFilterForm.value.filterType=='status'){
        if(this.userListFilterForm.value.statusSubfilter == null || 
          this.userListFilterForm.value.statusSubfilter == ''
        ){
          alert('Please add status sub filter.');
            return false;
        }
    }
    return true;
  }

  getAllRegisteredUser(pageNo:number, pageSize:number){
    this.service.getAllRegisteredUser(pageNo, pageSize).subscribe((res)=>{
      this.users = res.content;
      this.totalElements = res.totalElements;
      this.pageSize = res.pageSize;
    })
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
    this.getAllRegisteredUser(this.currentPage,this.pageSize);
  }

  goToPreviousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getAllRegisteredUser(this.currentPage,this.pageSize);
    }
  }

  goToNextPage(): void {
    const totalPages = Math.ceil(this.totalElements / this.pageSize);
    if (this.currentPage < totalPages - 1) {
      this.currentPage++;
      this.getAllRegisteredUser(this.currentPage,this.pageSize);
    }
  }


  
  displayStyle = "none"; 
  openPopup(email:any) {
    this.draftMailForm.value.recipientEmail=email;
    const student = this.draftMailForm.value;
    this.draftMailForm.patchValue(student);
    this.displayStyle = "block";
  }
  
  sendMail(){
    this.sendingmail = true;
    console.log(this.draftMailForm.value);
    this.service.sendSimpleMail(this.draftMailForm.value).subscribe((res)=>{
        if(res!=null){
          this.snackbar.open("Email Sent.", "Close",{duration:5000});
          this.closePopup();
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Either Email or Password is wrong.","Close", { duration: 5000 });
        this.closePopup();
      }
      if(err.status==404){
        this.users = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.closePopup();
      }
      if(err.status==200){
        this.users = null;
        console.log(err);
        this.snackbar.open('Email has been sent.',"Close", { duration: 5000 });
        this.closePopup();
        this.getAllRegisteredUser(this.currentPage,this.pageSize);
      }
   })
  }
  closePopup() {
    this.sendingmail = false;
    this.draftMailForm.value.subject=null;
    this.draftMailForm.value.message=null;
    this.displayStyle = "none"; 
  }

}
