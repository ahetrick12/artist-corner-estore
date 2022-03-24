import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cartitem';
import { CartItemService } from '../cartitem.service';
import { CartComponent } from "../cart/cart.component";

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

  findsum(): number{
    var total = 0;
    for(let j=0;j<this.cart.length;j++){
      total+= this.cart[j].item.price * this.cart[j].quantity;
    }
    return total;
  }

  clearCart(cart: CartItem[]): void{
    this.CartitemService.clearCart(cart);
  }
}
