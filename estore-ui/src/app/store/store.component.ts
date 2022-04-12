import { Component, OnInit } from '@angular/core';

import { Item } from '../item';
import { ItemService } from '../item.service';
import { CartService } from '../cart.service';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { compileDeclareInjectableFromMetadata } from '@angular/compiler';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css'],
})
export class StoreComponent implements OnInit {
  items: Item[] = [];
  selectedItem: Item = {
    id: -1,
    name: '',
    price: 0,
    stock: 10,
    S: 0,
    M: 0,
    L: 0,
    XL: 0,
    x920: 0,
    x1930: 0,
  };

  modal = 0;
  displayNone = 'none';
  scolor = 'white';
  mcolor = 'white';
  lcolor = 'white';
  xlcolor = 'white';
  x9color = 'white';
  x19color = 'white';
  showSizes = 'none';
  showFrames = 'none';
  selected = false;

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

  add(): void {
    this.cartService
      .addCartItem(
        this.authService.getCurrentUser().username,
        this.selectedItem
      )
      .subscribe();
    this.viewLess();
  }

  adminLoggedIn(): boolean {
    return this.authService.userIsAdmin();
  }

  viewMore(item: Item) {
    this.selectedItem.id = item.id;
    this.selectedItem.name = item.name;
    this.selectedItem.price = item.price;

    this.displayNone = 'Block';
    if (item.name.includes('Print')) {
      this.showFrames = 'Block';
      this.showSizes = 'none';
    } else {
      this.showSizes = 'Block';
      this.showFrames = 'none';
    }
  }

  viewLess() {
    this.displayNone = 'none';
    this.resetSelection();
  }

  select(str: string) {
    this.resetSelection();
    this.selected = true;

    switch (str) {
      case 'S':
        this.scolor = 'green';
        break;
      case 'M':
        this.mcolor = 'green';
        break;
      case 'L':
        this.lcolor = 'green';
        break;
      case 'XL':
        this.xlcolor = 'green';
        break;
      case 'x9':
        this.x9color = 'green';
        break;
      case 'x19':
        this.x19color = 'green';
        break;
    }
  }

  resetSelection(): void {
    this.selected = false;

    this.selectedItem.S = 0;
    this.selectedItem.M = 0;
    this.selectedItem.L = 0;
    this.selectedItem.XL = 0;
    this.selectedItem.x920 = 0;
    this.selectedItem.x1930 = 0;

    this.scolor = 'white';
    this.mcolor = 'white';
    this.lcolor = 'white';
    this.xlcolor = 'white';
    this.x9color = 'white';
    this.x19color = 'white';
  }
}
