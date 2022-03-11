import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Game, User} from "../../models";
import {CookieService} from "ngx-cookie-service";
import {Observable} from "rxjs";
import {AuthService} from "../services/auth.service";
import {GameService} from "../services/game.service";

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.css']
})
export class GameCardComponent implements OnInit {

  constructor() { }

  @Input() game: Game;
  @Output() addGameEvent: EventEmitter<Game> = new EventEmitter<Game>();
  isAdded: boolean = false;

  ngOnInit(): void {

  }

  async addToShoppingCart(): Promise<void> {
    this.isAdded = true;
    this.addGameEvent.emit(this.game);
  }

}
