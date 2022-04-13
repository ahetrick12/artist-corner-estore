import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Message } from './message';
import { CartItem } from './cartitem';
//import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class MessageService {
  private messagesUrl = 'http://localhost:8080/messages';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {}
  private messages: Observable<Message[]> = of();
  
  //private messageService: MessageService) { }

  /** GET heroes from the server */
  getMessages(): Observable<Message[]> {
    return this.http.get<Message[]>(this.messagesUrl).pipe(
      tap((_) => this.log('fetched items')),
      catchError(this.handleError<Message[]>('getMessages', []))
    );
  }

  
 /* GET items whose name contains search term */
 searchMessage(term: string): Observable<Message[]> {
  if (!term.trim()) {
    // if not search term, set searched to false to go back to using the full list and return empty item array
    return of([]);
  }

  // sets items variable to the search result
  this.messages = this.http.get<Message[]>(`${this.messagesUrl}/?name=${term}`).pipe(
    tap((x) =>
      x.length
        ? this.log(`found items matching "${term}"`)
        : this.log(`no items matching "${term}"`)
    ),
    catchError(this.handleError<Message[]>('searchMessages', []))
  );
  
  return this.messages;
}
  


  deleteMessage(id: number): Observable<Message> {
    const url = `${this.messagesUrl}/${id}`;

    return this.http.delete<Message>(url, this.httpOptions).pipe(
      tap((x) => {
        console.log(x);
        this.log(`deleted message id=${id}`);
    }),
      catchError(this.handleError<Message>('deleteMessage'))
    );
  }

  createMessage(mes: Message): Observable<Message> {
    return this.http.post<Message>(this.messagesUrl, mes).pipe(
      tap((_) => { 
        this.log('added message');
    }),
      catchError(this.handleError<Message>('addMessage'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a ItemService message with the MessageService */
  private log(message: string) {
    console.log(`MessageService: ${message}`);
  }
}
