import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { CartItem } from './cartitem'
import { Item } from './item'
//import { MessageService } from './message.service';


@Injectable({ providedIn: 'root' })
export class CartItemService {

  private cartUrl = 'http://localhost:8080/cart';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient){}
    //private messageService: MessageService) { }

  getCart(): Observable<CartItem[]> {
    return this.http.get<CartItem[]>(this.cartUrl)
      .pipe(
        tap(_ => this.log('fetched cart')),
        catchError(this.handleError<CartItem[]>('getCart', []))
      );
  }

  updateCartItem(item: CartItem): Observable<any> {
    return this.http.put(this.cartUrl, item, this.httpOptions).pipe(
      tap(_ => this.log(`updated item quantity=${item.quantity}`)),
      catchError(this.handleError<any>('updateCartItem'))
    );
  }

  deleteCartItem(item: Item): Observable<CartItem> {
    const url = `${this.cartUrl}/${item}`;
  
    return this.http.delete<CartItem>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted cartItem=${item}`)),
      catchError(this.handleError<CartItem>('deleteCartItem'))
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
    console.log(`CartService: ${message}`);
  }
}