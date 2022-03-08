import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../../models";

interface AuthResponse {
  token: string;
  user: User;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  tokenKey = 'authToken';
  user$ = new BehaviorSubject<User | null>(null);

  initUser() {
    const token = this.getToken();
    if (token) {
      const url = `http://localhost:9999/test/verify`;
      this.http.post<User>(url, {token}).subscribe(res => {
        this.setUser(res);
      });
    } else {
      this.setUser(null);
    }
  }

  getUser(): Observable<User | null> {
    console.log('getUser');
    return this.user$.asObservable();
  }

  async login(email: string, password: string): Promise<null | HttpErrorResponse> {
    const url = `http://localhost:9999/test/user/login`;
    return await this.http.post<AuthResponse>(url, {email, password}).toPromise().then((res) => {
      this.setUser(res.user);
      this.saveToken(res.token);
      return null;
    }).catch((err: HttpErrorResponse) => {
      console.log(err);
      return err;
    });
  }

  async signUp(user: User) {
    const url = `http://localhost:9999/test/user/signup`;
    return await this.http.post<AuthResponse>(url, {user}).toPromise().then((res) => {
      this.setUser(res.user);
      this.saveToken(res.token);
      return null;
    }).catch((err: HttpErrorResponse) => {
      console.log(err);
      return err;
    });
  }

  logOut() {
    localStorage.removeItem(this.tokenKey);
    this.setUser(null);
  }

  setUser(user: User | null): void {
    this.user$.next(user);
  }

  saveToken(token: string): void {
    if (token)
      localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

}
