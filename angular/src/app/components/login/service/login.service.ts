import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserCredentialsDTO} from "../../ainterfaces/user-credentials-dto";
import {API_URL} from "../../../app.module";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl: string;

  constructor(private http: HttpClient, @Inject(API_URL) gateway: string) {
    this.apiUrl = gateway + '/auth-service';
  }

  login(userCredentials: UserCredentialsDTO): Observable<any> {
    console.log('mati: ', this.apiUrl);
    return this.http.post<any>(this.apiUrl + "/guest/login", userCredentials, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
}
