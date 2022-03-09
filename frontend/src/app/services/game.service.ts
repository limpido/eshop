import { Injectable } from '@angular/core';
import {Game} from "../../models";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(
    private http: HttpClient
  ) { }

  async getAllGames() {
    const url = `http://localhost:9999/test/game/all`
    return await this.http.get<Array<Game>>(url).toPromise().then((res) => {
      console.log(res);
      return res;
    });
  }
}
