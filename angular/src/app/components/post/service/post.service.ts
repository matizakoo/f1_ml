import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {API_URL} from "../../../app.module";
import {PostDTO} from "../../models/post-dto";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl: string;

  constructor(private http: HttpClient, @Inject(API_URL) gateway: string) {
    this.apiUrl = gateway + '/auth-service';
  }

  addPost(topic: string, email: string): Observable<any> {
    const headers = {
      'auth-token': localStorage.getItem('auth-token') || ''
    };

    const params = {
      topic: topic,
      email: email
    };
    return this.http.post(this.apiUrl + '/user/post', null, { params, headers });
  }

  getAllPosts(): Observable<PostDTO[]> {
    const headers = {
      'auth-token': localStorage.getItem('auth-token') || ''
    };
    return this.http.get<PostDTO[]>(`${this.apiUrl}/user/post/all`, {headers});
  }
}
