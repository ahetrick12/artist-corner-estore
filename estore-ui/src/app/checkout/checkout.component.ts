import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cartitem';
import { CartService } from '../cart.service';
import { ItemService } from '../item.service';
import { CartComponent } from '../cart/cart.component';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { Item } from '../item';
import { FormBuilder } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  [x: string]: any;
  cart: CartItem[] = [];
  checkoutForm: any;

  constructor(
    private cartService: CartService,
    private userService: UserService,
    private authService: AuthService,
    private itemService: ItemService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.createForm();
  }

  createForm() {
    this.checkoutForm = this.formBuilder.group({
      fname: ['', [Validators.required]],
      lname: ['', [Validators.required]],
      phone: [
        '',
        [Validators.required, Validators.pattern('[0-9]{3}-[0-9]{3}-[0-9]{4}')],
      ],
      email: ['', [Validators.required, Validators.email]],
      country: ['', [Validators.required]],
      state: ['', [Validators.required]],
      address: ['', [Validators.required]],
      line2: [''],
      city: ['', [Validators.required]],
      zip: ['', [Validators.required]],
      name: ['', [Validators.required]],
      card: [
        '',
        [
          Validators.required,
          Validators.pattern('[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}'),
        ],
      ],
      exp: ['', [Validators.required]],
      cvc: ['', [Validators.required, Validators.pattern('[0-9]{3,4}')]],
    });
  }
  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    this.cartService.getCart().subscribe((cart) => {
      this.cart = cart;
    });
  }

  findsum(): number {
    var total = 0;
    for (let j = 0; j < this.cart.length; j++) {
      total +=
        this.cart[j].item.price *
        (this.cart[j].small +
          this.cart[j].medium +
          this.cart[j].large +
          this.cart[j].xlarge +
          this.cart[j].x920 +
          this.cart[j].x1930);
    }
    return total;
  }

  checkout(): void {
    console.log('CHECKOUT');
    for (let i = 0; i < this.cart.length; i++) {
      // Small
      if (this.cart[i].item.s >= this.cart[i].small) {
        var stock = this.cart[i].item.s - this.cart[i].small;
      } else {
        var diff = Math.abs(this.cart[i].item.s - this.cart[i].small);
        alert(
          this.cart[i].item.name +
            ' has ' +
            this.cart[i].item.s +
            ' stock; ' +
            diff +
            ' removed from cart.'
        );
        var stock = 0;
      }
      this.cart[i].item.s = stock;

      // Medium
      if (this.cart[i].item.m >= this.cart[i].medium) {
        var stock = this.cart[i].item.m - this.cart[i].medium;
      } else {
        var diff = Math.abs(this.cart[i].item.m - this.cart[i].medium);
        alert(
          this.cart[i].item.name +
            ' has ' +
            this.cart[i].item.m +
            ' stock; ' +
            diff +
            ' removed from cart.'
        );
        var stock = 0;
      }
      this.cart[i].item.m = stock;

      // Large
      if (this.cart[i].item.l >= this.cart[i].large) {
        var stock = this.cart[i].item.l - this.cart[i].large;
      } else {
        var diff = Math.abs(this.cart[i].item.l - this.cart[i].large);
        alert(
          this.cart[i].item.name +
            ' has ' +
            this.cart[i].item.l +
            ' stock; ' +
            diff +
            ' removed from cart.'
        );
        var stock = 0;
      }
      this.cart[i].item.l = stock;

      // Extra Large
      if (this.cart[i].item.xl >= this.cart[i].xlarge) {
        var stock = this.cart[i].item.xl - this.cart[i].xlarge;
      } else {
        var diff = Math.abs(this.cart[i].item.xl - this.cart[i].xlarge);
        alert(
          this.cart[i].item.name +
            ' has ' +
            this.cart[i].item.xl +
            ' stock; ' +
            diff +
            ' removed from cart.'
        );
        var stock = 0;
      }
      this.cart[i].item.xl = stock;

      // 9x12
      if (this.cart[i].item.x920 >= this.cart[i].x920) {
        var stock = this.cart[i].item.x920 - this.cart[i].x920;
      } else {
        var diff = Math.abs(this.cart[i].item.x920 - this.cart[i].x920);
        alert(
          this.cart[i].item.name +
            ' has ' +
            this.cart[i].item.x920 +
            ' stock; ' +
            diff +
            ' removed from cart.'
        );
        var stock = 0;
      }
      this.cart[i].item.x920 = stock;

      // 19x30
      if (this.cart[i].item.x1930 >= this.cart[i].x1930) {
        var stock = this.cart[i].item.x1930 - this.cart[i].x1930;
      } else {
        var diff = Math.abs(this.cart[i].item.x1930 - this.cart[i].x1930);
        alert(
          this.cart[i].item.name +
            ' has ' +
            this.cart[i].item.x1930 +
            ' stock; ' +
            diff +
            ' removed from cart.'
        );
        var stock = 0;
      }
      this.cart[i].item.x1930 = stock;

      this.saveItem(this.cart[i].item);
    }

    this.cart.splice(0, this.cart.length);
    this.cartService
      .clearCart(this.authService.getCurrentUser().username)
      .subscribe();
    this.checkoutForm.reset();
    this.router.navigate(['/confirmation']);
  }

  saveItem(item: Item): void {
    this.itemService.updateItem(item).subscribe();
  }
}
