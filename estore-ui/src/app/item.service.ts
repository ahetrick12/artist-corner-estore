import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Item } from './item';

@Injectable({ providedIn: 'root' })
export class ItemService {
  private itemsUrl = 'http://localhost:8080/items';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {}
  private items: Observable<Item[]> = of();
  private searched: boolean = false;
  //private messageService: MessageService) { }

  /** GET items from the server */
  getItems(): Observable<Item[]> {
    if (this.searched) {
      return this.items;
    }
    return this.http.get<Item[]>(this.itemsUrl).pipe(
      tap((_) => this.log('fetched items')),
      catchError(this.handleError<Item[]>('getItems', []))
    );
  }

  /* GET items whose name contains search term */
  searchItem(term: string): Observable<Item[]> {
    if (!term.trim()) {
      // if not search term, set searched to false to go back to using the full list and return empty item array
      this.searched = false;
      return of([]);
    }

    // sets items variable to the search result
    this.items = this.http.get<Item[]>(`${this.itemsUrl}/?name=${term}`).pipe(
      tap((x) =>
        x.length
          ? this.log(`found items matching "${term}"`)
          : this.log(`no items matching "${term}"`)
      ),
      catchError(this.handleError<Item[]>('searchItems', []))
    );
    this.searched = true; // sets searched to true to indicate to getItems to use the items list
    return this.items;
  }

  updateItem(item: Item): Observable<any> {
    return this.http.put(this.itemsUrl, item, this.httpOptions).pipe(
      tap((_) => this.log(`updated item id=${item.id}`)),
      catchError(this.handleError<any>('updateItem'))
    );
  }

  deleteItem(id: number): Observable<Item> {
    const url = `${this.itemsUrl}/${id}`;

    return this.http.delete<Item>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted item id=${id}`)),
      catchError(this.handleError<Item>('deleteItem'))
    );
  }

  addItem(item: Item): Observable<Item> {
    return this.http.post<Item>(this.itemsUrl, item, this.httpOptions).pipe(
      tap((newItem: Item) => this.log(`added item w/ id=${newItem.id}`)),
      catchError(this.handleError<Item>('addItem'))
    );
  }

  getItem(id: number): Observable<Item> {
    const url = `${this.itemsUrl}/${id}`;
    return this.http.get<Item>(url).pipe(
      tap((_) => this.log(`fetched item id=${id}`)),
      catchError(this.handleError<Item>(`getItem id=${id}`))
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
    console.log(`ItemService: ${message}`);
  }
}
