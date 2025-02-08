import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-teacher-list',
  standalone: false,
  
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.scss'
})
export class TeacherListComponent {

  teachers: any;

  constructor(
    private service : AdminService,
    private snackbar : MatSnackBar
  ){}

  ngOnInit(){
    this.getAllTeachers();
  }

  getAllTeachers(){
    this.service.getAllTeachers().subscribe((res)=>{
      this.teachers = res;
      console.log(this.teachers);
    })
  }

}
