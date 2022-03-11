import { Component, OnInit } from '@angular/core';
import {GameService} from "../services/game.service";
import {Game, User} from "../../models";
import {Observable} from "rxjs";
import {AuthService} from "../services/auth.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-game-store',
  templateUrl: './game-store.component.html',
  styleUrls: ['./game-store.component.css']
})
export class GameStoreComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private gameService: GameService,
    private cookieService: CookieService
  ) { }

  user$: Observable<User | null> = this.authService.getUser();
  user: User | null;
  games: Array<Game> = [];
  cartItems: Array<Game> = [];

  async ngOnInit(): Promise<void> {
    this.user$.subscribe(res => {
      this.user =  res;
    });
    await Promise.all([
      this.games = await this.gameService.getAllGames(),
      this.user?.uid ? this.cartItems = await this.gameService.getShoppingCartItems(this.user.uid) : null
    ]);

    console.log(this.cartItems);
  }

  async addGame(game: Game): Promise<void> {
    if (!this.user) {
      game = {
        ...game,
        qtyOrdered: 1
      };
      let items = this.cookieService.get("items") ? JSON.parse(this.cookieService.get("items")) : [];
      items.push(game);
      console.log(items);
      this.cookieService.set("items", JSON.stringify(items));
    } else if (this.user.uid) {
      let qty: number = 1;
      for (let cartItem of this.cartItems) {
        if (cartItem.gameId === game.gameId) {
          qty = cartItem.qtyOrdered ? cartItem.qtyOrdered + 1 : 1;
          break;
        }
      }
      await this.gameService.updateShoppingCartItem(this.user.uid, game.gameId, qty);
    }

  }

}
