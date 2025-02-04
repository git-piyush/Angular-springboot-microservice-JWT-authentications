import { Injectable } from '@angular/core';

const USER = "c_user";
const TOKEN = "c_token";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  
  constructor() { }

  public saveUser(user: any){
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user))
  }

  public saveToken(token: string){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static isAdminLoggedIn():boolean{
    if(this.getToken()==null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == "RCADM";
  }

  static isStudentLoggedIn():boolean{
    if(this.getToken()==null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == "RCSTD";
   }

   static getToken(){
    return window.localStorage.getItem(TOKEN);
   }

   static getUserRole(): any {
    const user = this.getUser();

    if(user==null){
      return null;
    }
    return user.userType;
  }

  static getUserId(){
    const user = this.getUser();
    if(user == null){
      return '';
    }else{
      return user.userId;
    }
  }

  static getUser():any {
    return JSON.parse(localStorage.getItem(USER)!);
  }

  static getUsername():any {
    const user = this.getUser();
    if(user == null){
      return '';
    }else{
      return user.userName;
    }
  }

  static logout(){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
}

static hasToken(): boolean{
  if(this.getToken() === null){
    return false;
  }
  return true;
}
}
