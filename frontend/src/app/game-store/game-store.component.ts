import { Component, OnInit } from '@angular/core';
import {GameService} from "../services/game.service";
import {Game} from "../../models";

@Component({
  selector: 'app-game-store',
  templateUrl: './game-store.component.html',
  styleUrls: ['./game-store.component.css']
})
export class GameStoreComponent implements OnInit {

  constructor(
    private gameService: GameService
  ) { }

  games: Array<Game> = [];

  ngOnInit(): void {
    this.getAllGames();
  }

  async getAllGames() {
    this.games = await this.gameService.getAllGames();
  }

}
