import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { User } from './user';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  static getCurrentUser(): User {
    return this.getCurrentUser();    
  }
  loggedInUser?: User;
  emptyUser: User = {
    username: '',
    password: '',
    cart: [],
  };

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

  userLoggedIn(): boolean {
    return this.loggedInUser != null;
  }

  getCurrentUser(): User {
    return this.loggedInUser != null ? this.loggedInUser : this.emptyUser;
  }

  userIsAdmin(): boolean {
    return this.loggedInUser != null && this.loggedInUser.username === 'admin';
  }
}
