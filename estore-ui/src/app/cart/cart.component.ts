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
    this.cart = this.cart.filter(i => i !== item);
    this.CartitemService.deleteCartItem(item.item.name).subscribe();
  }

  findsum(): number{   
    var total = 0;
      for(let j=0;j<this.cart.length;j++){   
         total+= this.cart[j].item.price * this.cart[j].quantity; 
      }  
      return total;
}
save(item: CartItem): void {
  if (this.cart) {
    if (item.quantity <= item.item.stock && item.quantity>0){
    this.CartitemService.updateCartItem(item)
      .subscribe(() => location.reload()
      );
  }
  else if (item.quantity < 0){
    alert("Entered quantity is less than zero.");
  }
  else{
    alert("Entered quantity is higher than current stock: " + item.item.stock);
  }
}
}
}