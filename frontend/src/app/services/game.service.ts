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
    const url = `http://localhost:9999/test/game/all`;
    return await this.http.get<Array<Game>>(url).toPromise().then((res) => {
      console.log(res);
      return res;
    });
  }

  async updateShoppingCartItem(uid: number, gameId: number, qtyOrdered: number) {
    console.log('update shopping cart', gameId);
    const url = `http://localhost:9999/test/shopping-cart/update`;
    await this.http.post<Game>(url, {uid, gameId, qtyOrdered}).toPromise().then((res) => {
      console.log(res);
    }).catch((err: HttpErrorResponse) => {
      console.log(err);
    });
  }

  async getShoppingCartItems(uid: number) {
    const url = `http://localhost:9999/test/shopping-cart/get`;
    return await this.http.post<Array<Game>>(url, {uid}).toPromise().then((res) => {
      console.log(res);
      return res;
    });
  }

  async gameQuery(searchParams: URLSearchParams) {
    // const searchParams = new URLSearchParams(param)
    console.log(searchParams);
    const url = `http://localhost:9999/test/game?`.concat(searchParams.toString());
    console.log(url);
    return await this.http.get<Array<Game>>(url).toPromise().then(res => {
      console.log(res);
      return res;
    })
  }

  getAllGenres() {
    const url = `http://localhost:9999/test/game/genres`;
    return this.http.get<Array<string>>(url).toPromise().then(res => {
      return res;
    })
  }
}
