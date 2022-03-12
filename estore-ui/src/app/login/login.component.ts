import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';

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
  expectedUser: User = {
    username: '',
    password: '',
  };

  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  findUser(username: string): Observable<User> {
    return this.userService.findUser(username);
  }

  onLogin(): void {
    this.findUser(this.currentUser.username).subscribe((user) => {
      let successful: boolean = true;
      let expectedUser: User = user;

      if (this.expectedUser == null) {
        this.expectedUser = {
          username: '',
          password: '',
        };
        successful = false;
      }

      if (this.expectedUser.password != this.currentUser.password) {
        successful = false;
      }

      this.loginStateAlert(successful, expectedUser);
    });
  }

  loginStateAlert(successful: boolean, expectedUser: User): void {
    alert(
      'Got: U - ' +
        this.currentUser.username +
        ' P - ' +
        this.currentUser.password +
        '\nExpected: U - ' +
        (expectedUser != null ? this.expectedUser.username : '') +
        ' P - ' +
        (expectedUser != null ? this.expectedUser.password : '') +
        '\n' +
        (successful ? 'MATCH!!!' : 'NO MATCH')
    );
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
