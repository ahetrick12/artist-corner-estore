import { Component, OnInit } from '@angular/core';

import { CartItem } from '../cartitem';
import { CartItemService } from '../cartitem.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: CartItem[] = [];

  constructor(private CartitemService: CartItemService) { }

  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    this.CartitemService.getCart()
    .subscribe(cart => this.cart = cart);
  }

  onDelete(item: CartItem): void {
    this.cart = this.cart.filter(h => h !== item);
    this.CartitemService.deleteCartItem(item.item).subscribe();
  }



}