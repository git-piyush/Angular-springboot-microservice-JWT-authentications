import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-refcode-setting',
  standalone: false,
  
  templateUrl: './refcode-setting.component.html',
  styleUrl: './refcode-setting.component.scss'
})
export class RefcodeSettingComponent {

  addRefCodeForm: FormGroup | undefined;
  refCodeSearchFilterForm: FormGroup | undefined;
  refCodes:any;
  refCodeCategoryList:any;
  filterValueInput : boolean = false;
  filterTextInput : boolean = false;
  totalElements = 0;
  pageSize = 3;
  currentPage = 0;
  pageSizeOptions = [3, 6, 12];

  Math: any;

  constructor(
    private service : AdminService,
    private snackbar : MatSnackBar,
    private fb : FormBuilder,
    private dialog: MatDialog,
    private router: Router
  ){
    this.Math = Math;
  }

  ngOnInit(){

    this.refCodeSearchFilterForm = this.fb.group({
      filterType:['', Validators.required],
      filterValue:['',Validators.required],
      filterText:['', [Validators.required, Validators.minLength(3)]]
    })

    this.addRefCodeForm = this.fb.group({
      refCode:['', Validators.required],
      category:['',Validators.required],
      longName:['', Validators.required],
      active:['', Validators.required]
    })

    this.getAllActiveRefCode(this.currentPage,this.pageSize);
  }

  myFunction(val: Event) {
    if((val.target as HTMLInputElement).value === 'refcode'){
      this.refCodeSearchFilterForm.value.filterValue = null;
      this.refCodeSearchFilterForm.value.filterText = null;
      this.filterValueInput = false;
      this.filterTextInput = true;
    }else if((val.target as HTMLInputElement).value === 'category'){
      this.getRefcodeDropDown();
        this.refCodeSearchFilterForm.value.filterValue = null;
      this.refCodeSearchFilterForm.value.filterText = null;
      this.filterValueInput = true;
      this.filterTextInput = false;
    }else if((val.target as HTMLInputElement).value === 'longname'){
      this.refCodeSearchFilterForm.value.filterValue = null;
      this.refCodeSearchFilterForm.value.filterText = null;
      this.filterValueInput = false;
      this.filterTextInput = true;
    }else if((val.target as HTMLInputElement).value === 'active'){
      this.refCodeSearchFilterForm.value.filterValue = null;
      this.refCodeSearchFilterForm.value.filterText = null;
      this.filterValueInput = false;
      this.filterTextInput = true;
    }
    const formVal = this.refCodeSearchFilterForm.value;
    this.refCodeSearchFilterForm.patchValue(formVal);
    console.log(formVal);
    console.log(this.refCodeSearchFilterForm.value);
  }

  getRefcodeDropDown(){
    console.log('getRefcodeDropDown');
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
    console.log(this.refCodeSearchFilterForm.value);
    if(!this.isSearchFormValid()){
      return;
    }
    if(this.isSearchFilterFormEmpty()){
      this.getAllActiveRefCode(this.currentPage,this.pageSize);
      return;
    }
    this.currentPage = 0;
    this.service.getRefCodeByFilter(this.currentPage,this.pageSize,this.refCodeSearchFilterForm.value.filterType,this.refCodeSearchFilterForm.value.filterValue,this.refCodeSearchFilterForm.value.filterText).subscribe((res)=>{
        console.log(res);
        if(res!=null){
          this.refCodes = res.content;
          this.totalElements = res.totalElements;
          this.pageSize = res.pageSize;
          this.snackbar.open("Student retrieved successfully.", "Close",{duration:5000});
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Some Error Occured.","Close", { duration: 5000 });
      }
      if(err.status==404){
        this.refCodes = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
      }
   })
  }

  refcodeByFilter1(){
    console.log(this.refCodeSearchFilterForm.value);
    if(!this.isSearchFormValid()){
      return;
    }
    if(this.isSearchFilterFormEmpty()){
      this.getAllActiveRefCode(this.currentPage,this.pageSize);
      return;
    }
    this.service.getRefCodeByFilter(this.currentPage,this.pageSize,this.refCodeSearchFilterForm.value.filterType,this.refCodeSearchFilterForm.value.filterValue,this.refCodeSearchFilterForm.value.filterText).subscribe((res)=>{
        console.log(res);
        if(res!=null){
          this.refCodes = res.content;
          this.totalElements = res.totalElements;
          this.pageSize = res.pageSize;
          this.snackbar.open("Student retrieved successfully.", "Close",{duration:5000});
        }
    }, (err:HttpErrorResponse) => {
      console.log(err);
      if(err.status==403){
        this.snackbar.open("Some Error Occured.","Close", { duration: 5000 });
      }
      if(err.status==404){
        this.refCodes = null;
        console.log(err);
        this.snackbar.open(err.error,"Close", { duration: 5000 });
      }
   })
  }

  isSearchFormValid():any{
    if(this.refCodeSearchFilterForm.value.filterType != null){
      if(this.refCodeSearchFilterForm.value.filterType === 'refcode' &&
        (this.refCodeSearchFilterForm.value.filterText === null || 
          this.refCodeSearchFilterForm.value.filterText === '')){
          alert('Filter Value can not be empty.');
          return false;
      }
      if(this.refCodeSearchFilterForm.value.filterType === 'category' &&
        (this.refCodeSearchFilterForm.value.filterValue === null ||
          this.refCodeSearchFilterForm.value.filterValue==='')){
        alert('Filter Text can not be empty.');
        return false;
      }
      if(this.refCodeSearchFilterForm.value.filterType === 'longname' &&
        (this.refCodeSearchFilterForm.value.filterText === null ||
         this.refCodeSearchFilterForm.value.filterText === '')){
        alert('Filter Value can not be empty.');
        return false;
      }
      if(this.refCodeSearchFilterForm.value.filterType === 'active' &&
        (this.refCodeSearchFilterForm.value.filterText === null ||
        this.refCodeSearchFilterForm.value.filterText === '')){
        alert('Filter Value can not be empty.');
        return false;
      }
    }
    return true;
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
      this.refcodeByFilter1();
    }
  }

  goToNextPage(): void {
    console.log(this.refCodeSearchFilterForm.value);
    const totalPages = Math.ceil(this.totalElements / this.pageSize);
    if (this.currentPage < totalPages - 1) {
      this.currentPage++;
      this.refcodeByFilter1();
      
    }
  }
  displayStyle = "none"; 
  openAddRefCodeForm(){
    this.getRefcodeDropDown();
    this.displayStyle = "block";
  }
  
  
  addRefCode(){
    console.log(this.addRefCodeForm.value);
    this.service.addRefCode(this.addRefCodeForm.value).subscribe((res)=>{
        if(res!=null){
          this.snackbar.open("Email Sent.", "Close",{duration:5000});
          this.closePopup();
          this.router.navigateByUrl("/refcodesetting");
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
    this.addRefCodeForm.value.refCode=null;
    this.addRefCodeForm.value.category=null;
    this.addRefCodeForm.value.longName=null;
    this.displayStyle = "none"; 
  }

}
