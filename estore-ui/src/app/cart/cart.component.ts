import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

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
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    this.userService
      .findUser(this.authService.getCurrentUser().username)
      .subscribe((user) => {
        this.cart = user.cart;
      });
  }

  onDelete(cartItem: CartItem): void {
    this.cart = this.cart.filter((i) => i !== cartItem);
    this.userService
      .deleteCartItem(this.authService.getCurrentUser().username, cartItem)
      .subscribe();
  }

  findsum(): number {
    var total = 0;
    for (let j = 0; j < this.cart.length; j++) {
      total += this.cart[j].item.price * this.cart[j].quantity;
    }
    return total;
  }

  save(cartItem: CartItem): void {
    if (this.cart) {
      this.userService
        .updateCartItem(this.authService.getCurrentUser().username, cartItem)
        .subscribe();
    }
  }
}
