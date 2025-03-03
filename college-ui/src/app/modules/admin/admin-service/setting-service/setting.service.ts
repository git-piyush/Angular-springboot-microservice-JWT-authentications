import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = ["http://localhost:8081/"]
@Injectable({
  providedIn: 'root'
})
export class SettingService {

  constructor(private http:HttpClient) { }

  getUserTypeDropDown(userCategory:string): Observable<any>{
    return this.http.get<[]>(BASIC_URL+`api/v1/admin/getrefcode/${userCategory}`,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getAllActiveRefCode(pageNo:number, pageSize:number): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/getrefcode?pageNo="+pageNo+"&pageSize="+pageSize,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getRefCodeByFilter(pageNo:number, pageSize:number, filterType:string, filterValue:string, filterText:string): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/getrefcode?pageNo="+pageNo+"&pageSize="+pageSize+"&filterType="+filterType+
      "&filterValue="+filterValue+"&filterText="+filterText,{
      headers: this.createAuhtorizationHeader()
    })
  }

  getCategoryDropDown(): Observable<any>{
    return this.http.get<[]>(BASIC_URL+"api/v1/admin/getrefcodecategorylist",{
      headers: this.createAuhtorizationHeader()
    })
  }

  addRefCode(refCodeRequest:any): Observable<any>{
    return this.http.post<[]>(BASIC_URL+"api/v1/admin/create",refCodeRequest,{
      headers: this.createAuhtorizationHeader()
    })
  }

  deleteRefCode(refCode: string):Observable<any> {
    return this.http.delete<[]>(BASIC_URL+`api/v1/admin/deleteRefCode/${refCode}`,{
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
