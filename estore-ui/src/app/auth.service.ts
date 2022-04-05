import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  loggedInUser?: User;
  val: boolean = false;
  private loginState = new Subject<boolean>();
  private loginStateObs = this.loginState.asObservable();

  constructor() {}

  updateLoginState(user: User | undefined): void {
    this.loggedInUser = user;
    this.val = this.loggedInUser != undefined ? true : false;
    this.loginState.next(this.val);

    console.log('UPDATE AUTH: ' + this.val);
  }

  getAuthStateObs(): Observable<boolean> {
    console.log('GET AUTH: ' + this.val);
    return this.loginStateObs;
  }

  getCurrentUser(): User | null {
    return this.loggedInUser != null ? this.loggedInUser : null;
  }

  userIsAdmin(): boolean {
    return this.loggedInUser != null && this.loggedInUser.username === 'admin';
  }
}
