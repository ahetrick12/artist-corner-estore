import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

import { User } from '../user';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  currentUser: User = {
    username: '',
    password: '',
  };

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private route: Router
  ) {}

  ngOnInit(): void {}

  findUser(username: string): Observable<User> {
    return this.userService.findUser(username);
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
        alert('Successfully logged in!');

        console.log(
          'USER: ' +
            this.currentUser.username +
            ' : ' +
            this.currentUser.password
        );

        this.authService.updateLoginState(this.currentUser);
        this.route.navigate(['']);
      } else {
        alert('Invalid username or password, please try again.');
      }
    });
  }

  onCreateAccount(): void {
    alert(
      'Creating Account... '
    ); /* + this.login.username + ' ' + this.login.password
    );
    this.login.username = '';
    this.login.password = '';*/
  }
}
