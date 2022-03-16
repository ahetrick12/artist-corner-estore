import {
  Component,
  ElementRef,
  HostListener,
  Renderer2,
  ViewChild,
} from '@angular/core';

import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Artist E-Store';

  isLoggedIn: boolean = false;
  accountDropdownToggle: boolean = false;

  @ViewChild('dropdown') dropdown?: ElementRef;
  @ViewChild('avatar') avatar?: ElementRef;

  constructor(private authService: AuthService, private renderer: Renderer2) {
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
  }
}
