import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-refcode-setting',
  standalone: false,
  
  templateUrl: './refcode-setting.component.html',
  styleUrl: './refcode-setting.component.scss'
})
export class RefcodeSettingComponent {

  draftMailForm: FormGroup | undefined;
  refCodeSearchFilterForm: FormGroup | undefined;
  refCodes:any;
  refCodeCategoryList:any;
  filterValueInput : boolean = false;
  filterTextInput : boolean = false;
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

    this.refCodeSearchFilterForm = this.fb.group({
      filterType:['', Validators.required],
      filterValue:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })

    this.draftMailForm = this.fb.group({
      recipientEmail:['', Validators.required],
      message:['',Validators.required],
      subject:['', Validators.required]
    })

    this.getAllActiveRefCode(this.currentPage,this.pageSize);
  }

  myFunction(val: Event) {
    if((val.target as HTMLInputElement).value === 'refcode'){
      this.filterValueInput = false;
      this.filterTextInput = true;
    }else if((val.target as HTMLInputElement).value === 'category'){
      this.getRefcodeDropDown(this.refCodeSearchFilterForm.value.filterType,
        this.refCodeSearchFilterForm.value.filterValue,
        this.refCodeSearchFilterForm.value.filterText);
      this.filterValueInput = true;
      this.filterTextInput = false;
    }else if((val.target as HTMLInputElement).value === 'longname'){
      this.filterValueInput = false;
      this.filterTextInput = true;
    }else if((val.target as HTMLInputElement).value === 'active'){
      this.filterValueInput = false;
      this.filterTextInput = true;
    }
  }

  getRefcodeDropDown(filterType:string, filterValue:string,filterText:string){
    this.service.getCategoryDropDown().subscribe((res)=>{
      console.log(res);
      this.refCodeCategoryList = res;
    })
  }

  isSearchFilterFormEmpty():any{
    if(this.refCodeSearchFilterForm.value.filterType==null
        && this.refCodeSearchFilterForm.value.filterValue==null
          && this.refCodeSearchFilterForm.value.filterText==null
    ){
      return true;
    }
  }

  refcodeByFilter(){
    alert('refcodeByFilter');
    if(!this.isSearchFormValid()){
      return;
    }
    if(this.isSearchFilterFormEmpty()){
      this.getAllActiveRefCode(this.currentPage,this.pageSize);
      return;
    }
    this.currentPage = 0;
    this.service.getAllRegistredUserByFilter(this.currentPage,this.pageSize,this.refCodeSearchFilterForm.value.filterType,this.refCodeSearchFilterForm.value.statusSubfilter,this.refCodeSearchFilterForm.value.filterText).subscribe((res)=>{
      this.isLoading = true;
        console.log(res);
        if(res!=null){
          this.refCodes = res.content;
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
        this.refCodes = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.isLoading = false;
      }
   })
  }

  isSearchFormValid():any{
    if(this.refCodeSearchFilterForm.value.filterType != null){
      if(this.refCodeSearchFilterForm.value.filterType === 'refcode' &&
        (this.refCodeSearchFilterForm.value.filterText === null || 
          this.refCodeSearchFilterForm.value.filterText === '')){
          alert('Filter Value can not be empty.');
      }
      if(this.refCodeSearchFilterForm.value.filterType === 'category' &&
        (this.refCodeSearchFilterForm.value.filterValue === null ||
          this.refCodeSearchFilterForm.value.filterValue==='')){
        alert('Filter Text can not be empty.');
      }
      if(this.refCodeSearchFilterForm.value.filterType === 'longname' &&
        this.refCodeSearchFilterForm.value.filterText === null){
        alert('Filter Value empty not possible');
      }
      if(this.refCodeSearchFilterForm.value.filterType === 'active' &&
        this.refCodeSearchFilterForm.value.filterText === null){
        alert('Filter Value empty not possible');
      }
    }
  }

  getAllActiveRefCode(pageNo:number, pageSize:number){
    this.service.getAllActiveRefCode(pageNo, pageSize).subscribe((res)=>{
      this.refCodes = res.content;
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
    this.getAllActiveRefCode(this.currentPage,this.pageSize);
  }

  goToPreviousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getAllActiveRefCode(this.currentPage,this.pageSize);
    }
  }

  goToNextPage(): void {
    const totalPages = Math.ceil(this.totalElements / this.pageSize);
    if (this.currentPage < totalPages - 1) {
      this.currentPage++;
      this.getAllActiveRefCode(this.currentPage,this.pageSize);
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
        this.refCodes = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
        this.closePopup();
      }
      if(err.status==200){
        this.refCodes = null;
        console.log(err);
        this.snackbar.open('Email has been sent.',"Close", { duration: 5000 });
        this.closePopup();
        this.getAllActiveRefCode(this.currentPage,this.pageSize);
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
