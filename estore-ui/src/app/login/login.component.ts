import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

import { User } from '../user';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  currentUser: User = {
    username: '',
    password: '',
    cart: [],
    imageLink: '../../assets/images/avatar.jpg',
  };

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private cartService: CartService,
    private route: Router
  ) {}

  ngOnInit(): void {}

  findUser(username: string): Observable<User> {
    return this.userService.findUser(username);
  }

  createUser(user: User): Observable<User> {
    return this.userService.createUser(user);
  }

  onLogin(): void {
    this.findUser(this.currentUser.username).subscribe((user) => {
      let successful: boolean = true;
      let expectedUser: User = user;

      if (
        expectedUser == null ||
        expectedUser.password != this.currentUser.password
      ) {
        successful = false;
      }

      // Conditional logic for valid and invalid logins
      if (successful) {
        this.authSuccess('Successfully logged in!');
      } else {
        alert('Invalid username or password, please try again.');
      }
    });
  }

  onCreateAccount(): void {
    this.createUser(this.currentUser).subscribe((user) => {
      let successful: boolean = true;

      if (user == null) {
        successful = false;
      }

      if (successful) {
        this.authSuccess('Successfully created account!');
      } else {
        alert('Invalid or already taken credentials, please try again.');
      }
    });
  }

  authSuccess(message: string): void {
    alert(message);

    console.log(
      'USER: ' + this.currentUser.username + ' : ' + this.currentUser.password
    );

    this.authService.updateLoginState(this.currentUser);
    this.route.navigate(['']);
  }
}
