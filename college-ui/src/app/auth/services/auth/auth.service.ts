import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import { Observable, map, tap } from 'rxjs';

const BASIC_URL = ['http://localhost:8081/']

const AUTH_HEADER = 'authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthService {  
  constructor(private http: HttpClient,
    private storage: StorageService) { }

    resetPassword(resetRequest: any): Observable<any> {
      return this.http.post<[]>(BASIC_URL + 'auth/reset-password', resetRequest)
    }
    
    sendOtp(signRequest: any): Observable<any> {
      return this.http.post<[]>(BASIC_URL + 'auth/sendotp', signRequest)
    }

    register(signRequest: any): Observable<any> {
      return this.http.post<[]>(BASIC_URL + 'auth/register', signRequest)
    }
  
  login(email: string, password:string): Observable<any>{
    return this.http.post(BASIC_URL + 'auth/token',{
      email,
      password
    },{observe:'response'})
      .pipe(
       tap(__ => this.log("User Authentication")),
       map((res:HttpResponse<any>)=>{
            this.storage.saveUser(res.body)
            //const tokenLength = res.headers.get(AUTH_HEADER).length;
            //const bearerToken = res.headers.get(AUTH_HEADER).substring(7, tokenLength);
            this.storage.saveToken(res.body.token);
            return res;
        }
       ))
      }
    log(message: string){
      console.log(message)
    }
}
