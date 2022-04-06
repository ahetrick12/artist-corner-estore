import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, EMPTY, find, Observable, of, tap } from 'rxjs';
import { CartItem } from './cartitem';
import { CartItemService } from './cartitem.service';
import { Item } from './item';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private usersUrl = 'http://localhost:8080/users';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {}

  findUser(username: string): Observable<User> {
    return this.http.get<User>(this.usersUrl + '/?username=' + username).pipe(
      tap((_) => this.log('found user')),
      catchError(this.handleError<User>('findUser'))
    );
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.usersUrl, user).pipe(
      tap((_) => this.log('created user')),
      catchError(this.handleError<User>('findUser'))
    );
  }

  // CART ITEM QUERIES

  updateCartItem(username: string, cartItem: CartItem): Observable<any> {
    this.findUser(username).subscribe((user) => {
      user.cart[cartItem.item.id] = cartItem;

      return this.http.put(this.usersUrl, user, this.httpOptions).pipe(
        tap((_) => this.log(`updated item quantity=${cartItem.quantity}`)),
        catchError(this.handleError<any>('updateCartItem'))
      );
    });

    return EMPTY;
  }

  deleteCartItem(username: string, cartItem: CartItem): Observable<CartItem> {
    this.findUser(username).subscribe((user) => {
      user.cart.splice(cartItem.item.id, 1);
    });

    return EMPTY;

    // const url = `${this.cartUrl}/${item}`;

    // return this.http.delete<CartItem>(url, this.httpOptions).pipe(
    //   tap((_) => this.log(`deleted cartItem=${item}`)),
    //   catchError(this.handleError<CartItem>('deleteCartItem'))
    // );
  }

  clearCart(username: string, cart: CartItem[]): void {
    for (let i = 0; i < cart.length; i++) {
      this.deleteCartItem(username, cart[i]).subscribe();
    }
  }

  addCartItem(username: string, item: Item): Observable<CartItem> {
    this.findUser(username).subscribe((user) => {
      let cartItem: CartItem = {
        item: item,
        quantity: 1,
      };
      this.log(cartItem.item.name);
      this.log(user.cart + '');
      user.cart.push(cartItem);

      return this.http.put(this.usersUrl, user, this.httpOptions).pipe(
        tap((_) => this.log(`updated item quantity=${cartItem.quantity}`)),
        catchError(this.handleError<any>('updateCartItem'))
      );
    });

    return EMPTY;

    // return this.http.post<CartItem>(this.cartUrl, item).pipe(
    //   tap((_) => this.log('added item to cart')),
    //   catchError(this.handleError<CartItem>('addItem'))
    // );
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

  /** Log a UserService message with the MessageService */
  private log(message: string) {
    console.log(`UserService: ${message}`);
  }
}
