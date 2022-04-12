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
  
  
  //private messageService: MessageService) { }

  /** GET heroes from the server */
  getMessages(): Observable<Message[]> {
    return this.http.get<Message[]>(this.messagesUrl).pipe(
      tap((_) => this.log('fetched items')),
      catchError(this.handleError<Message[]>('getMessages', []))
    );
  }

  


  deleteMessage(id: number): Observable<Message> {
    const url = `${this.messagesUrl}/${id}`;

    return this.http.delete<Message>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted message id=${id}`)),
      catchError(this.handleError<Message>('deleteMessage'))
    );
  }

  addMessage(message: Message): Observable<Message> {
    return this.http.post<Message>(this.messagesUrl, message, this.httpOptions).pipe(
      tap((newMessage: Message) => this.log(`added message w/ id=${newMessage.id}`)),
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
