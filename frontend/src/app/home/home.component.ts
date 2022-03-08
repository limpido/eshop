import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormControl} from "@angular/forms";
import {Observable, Subscription} from "rxjs";
import {User} from "../../models";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  user$: Observable<User | null> = this.authService.getUser();
  // authors: Array<AuthorName> = [
  //   {value: 'Tan Ah Teck', display: 'Ah Teck'},
  //   {value: 'Mohammad Ali', display: 'Ali'},
  //   {value: 'Kumar', display: 'Kumar'}
  // ];
  // books: Array<Book> = [];
  // authorForm: FormControl = new FormControl('all');
  // sub: Subscription;

  ngOnInit(): void {
    // this.getAll();
    // this.sub = this.authorForm.valueChanges.subscribe((value) => {
    //   console.log(value);
    //   if (value === 'all')
    //     this.getAll();
    //   else this.post(value);
    // })
  }
  //
  // post(author: string) {
  //   const url = 'http://localhost:9999/test/db';
  //   const header = new HttpHeaders({'Content-Type': 'application/json'});
  //   this.http.post<Book[]>(url, {author: author}, {headers: header}).subscribe((res) => {
  //     console.log(res);
  //     this.books = res;
  //   })
  // }
  //
  // getAll() {
  //   const url = 'http://localhost:9999/test/db';
  //   this.http.get<Book[]>(url).subscribe((res) => {
  //     console.log(res);
  //     this.books = res;
  //   })
  // }
}
