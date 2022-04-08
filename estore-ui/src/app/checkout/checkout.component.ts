import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cartitem';
import { CartItemService } from '../cartitem.service';
import { FormBuilder } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import {Router} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit {
  cart: CartItem[] = [];

  checkoutForm: any;

  constructor(private CartitemService: CartItemService,
              private formBuilder: FormBuilder,
              private router: Router) {
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

  checkout(cart: CartItem[]): void{
    this.CartitemService.clearCart(cart);
    this.checkoutForm.reset();
    this.router.navigate(['/homepage']);
  }
}
