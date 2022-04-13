import {
  Component,
  ElementRef,
  HostListener,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './auth.service';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Artist Corner';

  isLoggedIn: boolean = false;
  accountDropdownToggle: boolean = false;
  user : User = this.authService.getCurrentUser();

  @ViewChild('dropdown') dropdown?: ElementRef;
  @ViewChild('avatar') avatar?: ElementRef;

  constructor(
    private authService: AuthService,
    private renderer: Renderer2,
    private route: Router
  ) {
    this.authService.getAuthStateObs().subscribe((x) => (this.isLoggedIn = x));

    this.renderer.listen('window', 'click', (e: Event) => {
      if (
        this.avatar != undefined &&
        this.dropdown != undefined &&
        e.target !== this.avatar.nativeElement &&
        e.target !== this.dropdown.nativeElement
      ) {
        this.setAccountDropdown(false);
      }
    });
  }

  toggleAccountDropdown(): void {
    this.accountDropdownToggle = !this.accountDropdownToggle;
  }

  setAccountDropdown(val: boolean): void {
    this.accountDropdownToggle = val;
  }

  logout(): void {
    this.authService.updateLoginState(undefined);
    this.setAccountDropdown(false);
    this.route.navigate(['']);
  }
}
