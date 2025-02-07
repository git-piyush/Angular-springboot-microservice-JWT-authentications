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

  getAllStudents(): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/students",{
      headers: this.createAuhtorizationHeader()
    })
  }

  deleteStudent(studentId: any): Observable<any>{
    return this.http.delete<[]>(BASIC_URL+`api/admin/student/${studentId}`,{
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
