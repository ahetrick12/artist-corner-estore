import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cartitem';
import { CartService } from '../cart.service';
import { ItemService } from '../item.service';
import { CartComponent } from '../cart/cart.component';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';
import { Item } from '../item';
import { FormBuilder } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import {Router} from "@angular/router";

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
      fname: ['', [Validators.required]], lname: ['', [Validators.required]],
      phone: ['', [Validators.required, Validators.pattern("[0-9]{3}-[0-9]{3}-[0-9]{4}")]], email: ['', [Validators.required, Validators.email]],
      country: ['', [Validators.required]], state: ['', [Validators.required]],
      address: ['', [Validators.required]], line2: [''],
      city: ['', [Validators.required]], zip: ['', [Validators.required]],
      name: ['', [Validators.required]], card: ['', [Validators.required, Validators.pattern("[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}")]],
      exp: ['', [Validators.required]], cvc: ['', [Validators.required, Validators.pattern("[0-9]{3,4}")]]
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
      total += this.cart[j].item.price * this.cart[j].quantity;
    }
    return total;
  }

  checkout(): void {
    for (let i=0; i< this.cart.length ;i++){
      if (this.cart[i].item.stock>= this.cart[i].quantity){ 
        var stock = this.cart[i].item.stock - this.cart[i].quantity;
      } else{
        var diff = Math.abs(this.cart[i].item.stock - this.cart[i].quantity);
        alert((this.cart[i].item.name) + " has " + this.cart[i].item.stock + " stock; " + diff + " removed from cart.")
        var stock = 0;
      }
      this.cart[i].item.stock = stock;
      this.saveItem(this.cart[i].item);
    }
    this.cart.splice(0, this.cart.length);
    this.cartService
      .clearCart(this.authService.getCurrentUser().username)
      .subscribe();
    this.checkoutForm.reset();
    this.router.navigate(['/homepage']);
  }

  saveItem(item: Item): void {
    this.itemService.updateItem(item).subscribe();
  }
}
