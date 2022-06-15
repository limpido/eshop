import { Component, OnInit } from '@angular/core';
import {Game, User} from "../../models";
import {BehaviorSubject, Observable} from "rxjs";
import {AuthService} from "../services/auth.service";
import {CookieService} from "ngx-cookie-service";
import {LoginModalComponent} from "../login-modal/login-modal.component";
import {MatDialog} from "@angular/material/dialog";
import {GameService} from "../services/game.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private cookieService: CookieService,
    private gameService: GameService,
    public dialog: MatDialog,
  ) { }

  user$: Observable<User | null> = this.authService.getUser();
  user: User | null;
  games: Array<Game> = [];
  total: number = 0;
  isReady: boolean = false;

  ngOnInit(): void {
    const cookieItems = this.cookieService.get("items") ? JSON.parse(this.cookieService.get("items")) : [];
    this.user$.subscribe(async res => {
      this.user =  res;
      if (!this.user) {
        this.games = cookieItems;
      } else if (this.user.uid) {
        let dbItems = await this.gameService.getShoppingCartItems(this.user.uid) ?? [];
        for (let cookieItem of cookieItems) {
          let items = dbItems.filter(item => item.gameId === cookieItem.gameId);
          if (!items.length) {
            dbItems.push(cookieItem);
            await this.gameService.updateShoppingCartItem(this.user.uid, cookieItem.gameId, cookieItem.qtyOrdered);
          } else {
            items[0].qtyOrdered += cookieItem.qtyOrdered;
            if (items[0].qtyOrdered)
              await this.gameService.updateShoppingCartItem(this.user.uid, items[0].gameId, items[0].qtyOrdered);
          }
        }
        this.games = dbItems;
        // @ts-ignore
        this.games = this.games.filter((gameEl) => gameEl.qtyOrdered !== null && gameEl.qtyOrdered > 0);
        this.cookieService.delete("items");
      }
      this.calculateTotal();
    });

    this.isReady = true;
  }

  calculateTotal(): void {
    this.total = 0;
    for (let game of this.games) {
      if (game.qtyOrdered) {
        game.total = game.qtyOrdered * game.price;
        this.total += game.total;
      }
    }
  }

  saveItemsToCookie(): void {
    this.cookieService.set("items", JSON.stringify(this.games));
  }

  async onQtyOrderedChange(game: Game) {
    this.calculateTotal();
    if (!this.user)
      this.saveItemsToCookie();
    else if (this.user.uid && game.qtyOrdered !== undefined)
      await this.gameService.updateShoppingCartItem(this.user.uid, game.gameId, game.qtyOrdered);
  }

  increaseQtyOrdered(game: Game) {
    if (game.qtyOrdered && game.qtyOrdered > 0) {
      game.qtyOrdered++;
      this.onQtyOrderedChange(game);
    }
  }

  decreaseQtyOrdered(game: Game) {
    if (game.qtyOrdered && game.qtyOrdered > 0) {
      game.qtyOrdered--;
      if (game.qtyOrdered === 0) {
        this.games = this.games.filter((gameEl) => gameEl.qtyOrdered && gameEl.qtyOrdered > 0);
      }
      this.onQtyOrderedChange(game);
    }
  }

  checkOut(): void {
    if (!this.user)
      this.logIn();
  }

  logIn() {
    const loginModal = this.dialog.open(LoginModalComponent, {
      width: '400px'
    });
  }

}
