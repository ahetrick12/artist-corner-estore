import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Announcement } from './announcement';

@Injectable({ providedIn: 'root' })
export class AnnouncementsService {

  private announcementUrl = 'http://localhost:8080/announcements';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient){}

  getAnnouncements(): Observable<Announcement[]> {
    return this.http.get<Announcement[]>(this.announcementUrl)
      .pipe(
        tap(_ => this.log('fetched announcements')),
        catchError(this.handleError<Announcement[]>('getAnnouncements', []))
      );
  }

  updateAnnouncement(announcement: Announcement): Observable<any> {
    return this.http.put(this.announcementUrl, announcement, this.httpOptions).pipe(
      tap(_ => this.log(`updated announcement=${announcement.title}`)),
      catchError(this.handleError<any>('updateAnnouncement'))
    );
  }

  deleteAnnouncement(announcement: Announcement): Observable<Announcement> {
    const url = `${this.announcementUrl}/${announcement}`;

    return this.http.delete<Announcement>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted announcement=${announcement}`)),
      catchError(this.handleError<Announcement>('deleteAnnouncement'))
    );
  }

  addAnnouncement(announcement: Announcement): Observable<Announcement> {
    return this.http.post<Announcement>(this.announcementUrl, announcement).pipe(
      tap((_) => this.log('added announcement')),
      catchError(this.handleError<Announcement>('addAnnouncement'))
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

  /** Log a AnnouncementService message with the MessageService */
  private log(message: string) {
    console.log(`AnnouncementService: ${message}`);
  }
}
