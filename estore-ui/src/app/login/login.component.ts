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

  constructor(private userService: UserService) {}

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

      this.loginStateAlert(successful);
    });
  }

  loginStateAlert(successful: boolean): void {
    alert(
      successful
        ? 'Successfully logged in!'
        : 'Invalid username or password, please try again.'
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
