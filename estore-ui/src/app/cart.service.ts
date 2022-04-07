import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { EMPTY, Observable, of } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';

import { CartItem } from './cartitem';
import { Item } from './item';
import { User } from './user';
import { AuthService } from './auth.service';
import { UserService } from './user.service';
//import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class CartService {
  private usersUrl = 'http://localhost:8080/users';

  private noUserCart: CartItem[] = [];

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private userService: UserService
  ) {}

  getCart(): Observable<any> {
    if (this.authService.userLoggedIn()) {
      // Remove noUserCart items when someone is logged in
      this.noUserCart = [];

      return this.userService
        .findUser(this.authService.getCurrentUser().username)
        .pipe(
          switchMap((user) => {
            return of(user.cart);
          })
        );
    } else {
      return of(this.noUserCart);
    }
  }

  updateCartItem(username: string, cartItem: CartItem): Observable<any> {
    if (this.authService.userLoggedIn()) {
      return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
        switchMap((user) => {
          let filteredCart = user.cart.filter(
            (item) => item.item.id === cartItem.item.id
          );

          // Never called unless manually changing quantity so we don't need to check if filteredCart[0] exists
          user.cart[user.cart.indexOf(filteredCart[0])] = cartItem;
          return this.http.put(this.usersUrl, user, this.httpOptions);
        }),
        catchError(this.handleError<User>('updateCartItem'))
      );
    } else {
      let filteredCart = this.noUserCart.filter(
        (item) => item.item.id === cartItem.item.id
      );

      this.noUserCart[this.noUserCart.indexOf(filteredCart[0])] = cartItem;

      return EMPTY;
    }
  }

  deleteCartItem(username: string, cartItem: CartItem): Observable<any> {
    if (this.authService.userLoggedIn()) {
      return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
        switchMap((user) => {
          const index = user.cart.indexOf(cartItem);
          user.cart.splice(index, 1);
          return this.http.put(this.usersUrl, user, this.httpOptions);
        }),
        catchError(this.handleError<User>('deleteCartItem'))
      );
    } else {
      const index = this.noUserCart.indexOf(cartItem);
      this.noUserCart.splice(index, 1);

      return EMPTY;
    }
  }

  clearCart(username: string): Observable<any> {
    if (this.authService.userLoggedIn()) {
      return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
        switchMap((user) => {
          user.cart = [];
          return this.http.put(this.usersUrl, user, this.httpOptions);
        }),
        catchError(this.handleError<User>('clearCart'))
      );
    } else {
      this.noUserCart = [];

      return EMPTY;
    }
  }

  addCartItem(username: string, item: Item): Observable<any> {
    let cartItem: CartItem = {
      item: item,
      quantity: 1,
    };

    if (this.authService.userLoggedIn()) {
      return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
        switchMap((user) => {
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
    } else {
      let filteredCart = this.noUserCart.filter(
        (cartItem) => cartItem.item.id === item.id
      );

      if (filteredCart.length > 0) {
        this.noUserCart[this.noUserCart.indexOf(filteredCart[0])].quantity++;
      } else {
        this.noUserCart.push(cartItem);
      }

      return EMPTY;
    }
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
