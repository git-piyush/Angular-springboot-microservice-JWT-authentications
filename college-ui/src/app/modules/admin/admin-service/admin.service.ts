import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = ["http://localhost:8081/"]

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http:HttpClient) { }

  getAllRegistredUserByFilter(pageNo:number, pageSize:number, filterType:string, statusSubfilter:string, filterText:string): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/registereduser?pageNo="+pageNo+"&pageSize="+pageSize+"&filterType="+filterType+
      "&statusSubfilter="+statusSubfilter+"&filterText="+filterText,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getAllRegisteredUser(pageNo:number, pageSize:number): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/registereduser?pageNo="+pageNo+"&pageSize="+pageSize,{
      headers: this.createAuhtorizationHeader()
    })
  }

  studentListByFilter(pageNo:number, pageSize:number, filterType:string, statusSubfilter:string, filterText:string): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/students?pageNo="+pageNo+"&pageSize="+pageSize+"&filterType="+filterType+
      "&statusSubfilter="+statusSubfilter+"&filterText="+filterText,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getAllStudents(pageNo:number, pageSize:number): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/students?pageNo="+pageNo+"&pageSize="+pageSize,{
      headers: this.createAuhtorizationHeader()
    })
  }

  teacherListByFilter(pageNo:number, pageSize:number,teacherFilter:any):Observable<any>{
    return this.http.post<[]>(BASIC_URL + "api/v1/admin/teachers?pageNo="+pageNo+"&pageSize="+pageSize,teacherFilter,{
      headers: this.createAuhtorizationHeader()
    });
  }

  sendSimpleMail(emailRequest:any):Observable<any>{
    return this.http.post<[]>(BASIC_URL + "api/v1/notification/sendmail",emailRequest,{
      headers: this.createAuhtorizationHeader()
    });
  }

  deleteStudent(studentId: any): Observable<any>{
    return this.http.delete<[]>(BASIC_URL+`api/admin/student/${studentId}`,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getAllTeachers(pageNo:number, pageSize:number): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/teachers?pageNo="+pageNo+"&pageSize="+pageSize,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getRegisteredUserDetailsById(userId: any):Observable<any>{
    return this.http.get<[]>(BASIC_URL+`api/v1/admin/userdetails/${userId}`,{
      headers: this.createAuhtorizationHeader()
    })
  }
  createStudentVacancy(userId: any):Observable<any>{
    return this.http.get<[]>(BASIC_URL+`api/v1/admin/userdetails/${userId}`,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getAllActiveRefCode(pageNo:number, pageSize:number): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/refcode/getrefcode?pageNo="+pageNo+"&pageSize="+pageSize,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getRefCodeByFilter(pageNo:number, pageSize:number, filterType:string, filterValue:string, filterText:string): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/refcode/getrefcode?pageNo="+pageNo+"&pageSize="+pageSize+"&filterType="+filterType+
      "&filterValue="+filterValue+"&filterText="+filterText,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getCategoryDropDown(): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/refcode/getrefcodecategorylist",{
      headers: this.createAuhtorizationHeader()
    })
  }

  addRefCode(refCodeRequest:any): Observable<any>{
    return this.http.post<[]>(BASIC_URL+"api/v1/refcode/create",refCodeRequest,{
      headers: this.createAuhtorizationHeader()
    })
  }

  deleteRefCode(refCode: string):Observable<any> {
    return this.http.delete<[]>(BASIC_URL+`api/v1/refcode/deleteRefCode/${refCode}`,{
      headers: this.createAuhtorizationHeader()
    })
  }


  private createAuhtorizationHeader() {
    const jwtToken = localStorage.getItem('c_token');
    if (jwtToken) {
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      )
    } else {
      console.log("JWT token not found in local storage");
    }
    return null;
  }

}
