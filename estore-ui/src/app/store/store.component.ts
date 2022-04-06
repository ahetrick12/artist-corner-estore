import { Component, OnInit } from '@angular/core';

import { Item } from '../item';
import { CartItem } from '../cartitem';
import { ItemService } from '../item.service';
import { CartItemService } from '../cartitem.service';
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
    private userService: UserService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.getItems();
  }

  getItems(): void {
    this.itemService.getItems().subscribe((items) => (this.items = items));
  }

  add(item: Item): void {
    this.userService
      .addCartItem(this.authService.getCurrentUser().username, item)
      .subscribe();
  }

  adminLoggedIn(): boolean {
    return this.authService.userIsAdmin();
  }
}
