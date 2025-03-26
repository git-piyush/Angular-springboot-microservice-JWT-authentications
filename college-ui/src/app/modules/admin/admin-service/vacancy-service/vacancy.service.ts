import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = ["http://localhost:8081/"]

@Injectable({
  providedIn: 'root'
})
export class VacancyService {

  constructor(private http:HttpClient) { }

  createVacancy(vacancyForm: any):Observable<any>{
    return this.http.post<[]>(BASIC_URL+"api/v1/admin/vacancy",vacancyForm,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getAllVacancy(): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/vacancy",{
      headers: this.createAuhtorizationHeader()
    })
  }

  register(signRequest: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + 'auth/register', signRequest)
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
