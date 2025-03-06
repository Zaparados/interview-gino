import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

export interface Word {
  id: bigint;
  word: string;
  difficulty: string;
  hint: string;
}

@Injectable({
  providedIn: 'root'
})
export class WordService {


  constructor(private http: HttpClient) {}

  public findAll(): Observable<Word[]> {
    return this.http.get<Word[]>("words");
  }

  public save(word: Word) {
    return this.http.post<Word>("words", word);
  }
}
