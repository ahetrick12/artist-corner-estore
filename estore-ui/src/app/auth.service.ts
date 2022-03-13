import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  loggedInUser?: User;
  private loginState = new Subject<boolean>();
  private loginStateObs = this.loginState.asObservable();

  constructor() {}

  updateLoginState(user: User): void {
    this.loggedInUser = user;
    this.loginState.next(this.loggedInUser != null ? true : false);
    console.log('UPDATE: ' + this.loginState);
  }

  getAuthStateObs(): Observable<boolean> {
    console.log('GET: ' + this.loginState);
    return this.loginStateObs;
  }

  getCurrentUser(): User | null {
    return this.loggedInUser != null ? this.loggedInUser : null;
  }
}
