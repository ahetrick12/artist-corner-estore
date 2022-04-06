import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { CartService } from '../cart.service';

import { CartItem } from '../cartitem';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cart: CartItem[] = [];

  constructor(
    private userService: UserService,
    private cartService: CartService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    if (this.authService.userLoggedIn()) {
      this.userService
        .findUser(this.authService.getCurrentUser().username)
        .subscribe((user) => {
          this.cart = user.cart;
        });
    }
  }

  onDelete(cartItem: CartItem): void {
    this.cart = this.cart.filter((i) => i !== cartItem);
    this.cartService
      .deleteCartItem(this.authService.getCurrentUser().username, cartItem)
      .subscribe();
  }

  findSum(): number {
    var total = 0;
    for (let j = 0; j < this.cart.length; j++) {
      total += this.cart[j].item.price * this.cart[j].quantity;
    }
    return total;
  }

  save(cartItem: CartItem): void {
    if (this.cart) {
      this.cartService
        .updateCartItem(this.authService.getCurrentUser().username, cartItem)
        .subscribe();
    }
  }
}
