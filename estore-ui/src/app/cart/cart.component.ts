import { Component, OnInit } from '@angular/core';

import { Item } from '../item';
import { Cart } from '../cart';
import { CartItem } from '../cartitem';

import { ItemService } from '../item.service';

@Component({
  selector: 'app-store',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  cart: CartItem[] = [];

  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    this.getCart();
  }
  getCart() : void {
    this.itemService.getCart()
    .subscribe(cart => this.cart = cart);
  }


}