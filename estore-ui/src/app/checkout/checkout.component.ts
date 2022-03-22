import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cartitem';
import { CartItemService } from '../cartitem.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cart: CartItem[] = [];

  constructor(private CartitemService: CartItemService) { }

  ngOnInit(): void {
    this.getItems()
  }

  getItems(): void {
    this.CartitemService.getCart()
      .subscribe(cart => this.cart = cart);
  }

}
