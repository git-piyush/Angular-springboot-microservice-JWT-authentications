import { Component } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Reva University';
  isAdminLoggedIn: boolean;
  isStudentLoggedIn: boolean;
  whitetext:boolean;
  name:string='';
  constructor(private router : Router,private storage: StorageService){}

  ngOnInit(){
    this.getUser();
    this.updateUserLoggedStatus();
    this.router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        this.updateUserLoggedStatus();
      }
    })
  }

  toggleDarkMode() {
    document.documentElement.classList.toggle('dark');
    this.whitetext = !this.whitetext;
  }

  getUser(){
    this.name = StorageService.getUsername();
    console.log("Loggedin User: "+this.name);
  }

  private updateUserLoggedStatus(){
    this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
    this.isStudentLoggedIn = StorageService.isStudentLoggedIn();
  }

  logout() {
      StorageService.logout();
      this.router.navigateByUrl("/login");
    }

    viewProfile(){
      console.log('view profile');
    }
  
}
