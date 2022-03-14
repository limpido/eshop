import {Component, OnDestroy, OnInit} from '@angular/core';
import {GameService} from "../services/game.service";
import {Game, User} from "../../models";
import {Observable, Subscription} from "rxjs";
import {AuthService} from "../services/auth.service";
import {CookieService} from "ngx-cookie-service";
import {FormControl} from "@angular/forms";

// export interface searchParams {
//   price?: string,
//   title?: string,
//   genre?: string
// }

@Component({
  selector: 'app-game-store',
  templateUrl: './game-store.component.html',
  styleUrls: ['./game-store.component.css']
})
export class GameStoreComponent implements OnInit, OnDestroy {

  constructor(
    private authService: AuthService,
    private gameService: GameService,
    private cookieService: CookieService
  ) { }

  user$: Observable<User | null> = this.authService.getUser();
  user: User | null;
  allGames: Array<Game> = [];
  games: Array<Game> = [];
  cartItems: Array<Game> = [];
  priceFc: FormControl = new FormControl('all');
  titleFc: FormControl = new FormControl('');
  genreFc: FormControl = new FormControl('all');
  searchParams: any = {};
  genres: Array<String> = [];
  sub1: Subscription;
  sub2: Subscription;

  async ngOnInit(): Promise<void> {
    this.user$.subscribe(res => {
      this.user =  res;
    });
    await Promise.all([
      this.genres = await this.gameService.getAllGenres(),
      this.allGames = await this.gameService.getAllGames(),
      this.user?.uid ? this.cartItems = await this.gameService.getShoppingCartItems(this.user.uid) : null
    ]);
    this.games = [...this.allGames];
    console.log(this.cartItems);

    this.sub1 = this.priceFc.valueChanges.subscribe(async (value) => {
      if (value === 'all') {
        this.games = [...this.allGames];
      } else {
        console.log(value);
        this.searchParams['price'] = value;
        // const searchParams = new URLSearchParams(this.searchParams);
        this.games = await this.gameService.gameQuery(new URLSearchParams(this.searchParams));
      }
    });

    this.sub2 = this.genreFc.valueChanges.subscribe(async (value) => {
      if (value === 'all') {
        this.games = [...this.allGames];
      } else {
        console.log(value);
        this.searchParams['genre'] = value;
        this.games = await this.gameService.gameQuery(new URLSearchParams(this.searchParams));
      }
    });
  }

  async searchTitle() {
    if (this.titleFc.value === '') {
      delete this.searchParams['title'];
      console.log(this.searchParams);
      console.log(Object.keys(this.searchParams).length);
      if (!Object.keys(this.searchParams).length) {
        this.games = [...this.allGames];
      } else {
        this.games = await this.gameService.gameQuery(new URLSearchParams(this.searchParams));
      }
    } else {
      this.searchParams['title'] = this.titleFc.value.replace("'", "''");
      this.games = await this.gameService.gameQuery(new URLSearchParams(this.searchParams));
    }
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

  ngOnDestroy(): void {
    this.sub1?.unsubscribe();
    this.sub2?.unsubscribe();
  }

}
