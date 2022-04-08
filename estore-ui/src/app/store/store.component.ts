import { Component, OnInit } from '@angular/core';

import { Item } from '../item';
import { ItemService } from '../item.service';
import { CartService } from '../cart.service';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css'],
})
export class StoreComponent implements OnInit {
  items: Item[] = [];

  constructor(
    private itemService: ItemService,
    private cartService: CartService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    this.itemService.getItems().subscribe((items) => (this.items = items));
  }

  add(item: Item): void {
    // chek if item.stock> cart.item.quantity ???? cant figure out how to do taht
      this.cartService
      .addCartItem(this.authService.getCurrentUser().username, item)
      .subscribe();
  }

  adminLoggedIn(): boolean {
    return this.authService.userIsAdmin();
  }

  itemsSize(){
    return this.items.length;
  }
}
