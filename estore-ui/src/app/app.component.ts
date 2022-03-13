import { Component } from '@angular/core';

import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Artist E-Store';
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService) {
    this.authService.getAuthStateObs().subscribe((x) => (this.isLoggedIn = x));
  }

  logout(): void {
    this.authService.updateLoginState(undefined);
  }
}
