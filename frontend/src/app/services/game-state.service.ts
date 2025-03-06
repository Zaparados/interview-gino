import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WordService } from './word.service';
import { Word } from './word.service'

export interface Gamestate {
  word: Word;
  solution: string;
  guessedLetters: string;
  lives: number;
}

@Injectable({
  providedIn: 'root'
})
export class GameStateService {

  title = 'Hangman';
  greeting = {};
  constructor(private http: HttpClient) {
  }

  public newGamestate(): Observable<Gamestate> {
    return this.http.get<Gamestate>("gamestate");
  }

  public tryCharacter(character: string, gamestate: Gamestate): Observable<Gamestate> {
    const payload = { character, gamestate };
    return this.http.post<Gamestate>("gamestate", payload);
  }
}
