import { Component, OnInit } from '@angular/core';
import { filter } from 'rxjs';
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
    this.cartService.getCart().subscribe((cart) => {
      this.cart = cart;
    });
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
      total +=
        this.cart[j].item.price *
        (this.cart[j].s +
          this.cart[j].m +
          this.cart[j].l +
          this.cart[j].xl +
          this.cart[j].x920 +
          this.cart[j].x1930);
    }
    return total;
  }
  save(cartItem: CartItem): void {
    console.log(cartItem);
    if (this.cart) {
      this.cartService
        .updateCartItem(this.authService.getCurrentUser().username, cartItem)
        .subscribe();
    }
  }

  validateInput(cartItem: CartItem, selection: string): void {
    let filteredCart = this.cart.filter(
      (item) => item.item.id === cartItem.item.id
    );
    let index = this.cart.indexOf(filteredCart[0]);

    let num = 0;
    let stock = 0;

    switch (selection) {
      case 'S':
        num = this.cart[index].s;
        stock = this.cart[index].item.s;
        break;
      case 'M':
        num = this.cart[index].m;
        stock = this.cart[index].item.m;
        break;
      case 'L':
        num = this.cart[index].l;
        stock = this.cart[index].item.l;
        break;
      case 'XL':
        num = this.cart[index].xl;
        stock = this.cart[index].item.xl;
        break;
      case 'x9':
        num = this.cart[index].x920;
        stock = this.cart[index].item.x920;
        break;
      case 'x19':
        num = this.cart[index].x1930;
        stock = this.cart[index].item.x1930;
        break;
    }

    num = Math.round(num);

    if (num <= 0) {
      num = 1;
    } else if (num > stock) {
      num = stock;
    }

    switch (selection) {
      case 'S':
        this.cart[index].s = num;
        break;
      case 'M':
        this.cart[index].m = num;
        break;
      case 'L':
        this.cart[index].l = num;
        break;
      case 'XL':
        this.cart[index].xl = num;
        break;
      case 'x9':
        this.cart[index].x920 = num;
        break;
      case 'x19':
        this.cart[index].x1930 = num;
        break;
    }
  }
}
