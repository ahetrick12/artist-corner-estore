import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cartitem';
import { CartService } from '../cart.service';
import { CartComponent } from '../cart/cart.component';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  cart: CartItem[] = [];

  constructor(
    private cartService: CartService,
    private userService: UserService,
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

  findsum(): number {
    var total = 0;
    for (let j = 0; j < this.cart.length; j++) {
      total += this.cart[j].item.price * this.cart[j].quantity;
    }
    return total;
  }

  checkout(): void {
    this.cart.splice(0, this.cart.length);
    this.cartService
      .clearCart(this.authService.getCurrentUser().username)
      .subscribe();
  }
}
