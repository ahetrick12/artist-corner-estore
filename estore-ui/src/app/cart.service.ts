import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, mergeMap, tap } from 'rxjs/operators';

import { CartItem } from './cartitem';
import { Item } from './item';
import { User } from './user';
//import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class CartService {
  private usersUrl = 'http://localhost:8080/users';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {}
  //private messageService: MessageService) { }

  // Maybe not needed - instead just get cart from current user?
  // getCart(): Observable<CartItem[]> {
  //   return this.http.get<CartItem[]>(this.cartUrl).pipe(
  //     tap((_) => this.log('fetched cart')),
  //     catchError(this.handleError<CartItem[]>('getCart', []))
  //   );
  // }

  updateCartItem(username: string, cartItem: CartItem): Observable<any> {
    return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
      mergeMap((user) => {
        let filteredCart = user.cart.filter(
          (item) => item.item.id === cartItem.item.id
        );

        // Never called unless manually changing quantity so we don't need to check if filteredCart[0] exists
        user.cart[user.cart.indexOf(filteredCart[0])] = cartItem;
        return this.http.put(this.usersUrl, user, this.httpOptions);
      }),
      catchError(this.handleError<User>('updateCartItem'))
    );
  }

  deleteCartItem(username: string, cartItem: CartItem): Observable<any> {
    return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
      mergeMap((user) => {
        const index = user.cart.indexOf(cartItem);
        user.cart.splice(index, 1);
        return this.http.put(this.usersUrl, user, this.httpOptions);
      }),
      catchError(this.handleError<User>('deleteCartItem'))
    );
  }

  clearCart(username: string, cart: CartItem[]): void {
    for (let i = 0; i < cart.length; i++) {
      this.deleteCartItem(username, cart[i]).subscribe();
    }
  }

  addCartItem(username: string, item: Item): Observable<any> {
    let cartItem: CartItem = {
      item: item,
      quantity: 1,
    };

    return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
      mergeMap((user) => {
        let filteredCart = user.cart.filter(
          (cartItem) => cartItem.item.id === item.id
        );

        if (filteredCart.length > 0) {
          user.cart[user.cart.indexOf(filteredCart[0])].quantity++;
        } else {
          user.cart.push(cartItem);
        }

        return this.http.put(this.usersUrl, user, this.httpOptions);
      }),
      catchError(this.handleError<User>('addCartItem'))
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
