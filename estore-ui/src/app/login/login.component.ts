import { Component, OnInit } from '@angular/core';
import { LoginInfo } from '../login_info';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login: LoginInfo = {
    username: '',
    password: '',
  };

  constructor() {}

  ngOnInit(): void {}

  onLogin(): void {
    alert('Logging in... ' + this.login.username + ' ' + this.login.password);
    this.login.username = '';
    this.login.password = '';
  }

  onCreateAccount(): void {
    alert(
      'Creating Account... ' + this.login.username + ' ' + this.login.password
    );
    this.login.username = '';
    this.login.password = '';
  }
}
