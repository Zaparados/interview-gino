import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import { GameStateService, Gamestate } from './services/game-state.service'
import {NgIf} from '@angular/common';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    FormsModule,
    NgIf
  ],
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Hangman';
  gamestate: Gamestate;
  inputText: string = '';
  showHint: boolean = false;

  constructor(private gameStateService: GameStateService) {
  }

  hint() {
    this.showHint = true;
  }

  submitCharacter() {
    this.gameStateService.tryCharacter(this.inputText, this.gamestate).subscribe(data => {
      this.gamestate = data;
    });
    this.inputText = '';
  }

  newWord() {
    this.showHint = false;
    this.gameStateService.newGamestate().subscribe(data => {
      this.gamestate = data;
    });
  }

  ngOnInit() {
    this.newWord();
  }
}
