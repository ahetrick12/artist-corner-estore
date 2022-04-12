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
    s: 0,
    m: 0,
    l: 0,
    xl: 0,
    x920: 0,
    x1930: 0,
    image: '',
  };

  modal = 0;
  displayNone = 'none';

  scolor = 'white';
  mcolor = 'white';
  lcolor = 'white';
  xlcolor = 'white';
  x9color = 'white';
  x19color = 'white';

  selection = '';

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
        this.selectedItem,
        this.selection
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
    this.selectedItem.stock = item.stock;
    this.selectedItem.s = item.s;
    this.selectedItem.m = item.m;
    this.selectedItem.l = item.l;
    this.selectedItem.xl = item.xl;
    this.selectedItem.x920 = item.x920;
    this.selectedItem.x1930 = item.x1930;
    this.selectedItem.image = item.image;

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
    this.selection = str;

    switch (str) {
      case 'S':
        this.scolor = 'gray';
        break;
      case 'M':
        this.mcolor = 'gray';
        break;
      case 'L':
        this.lcolor = 'gray';
        break;
      case 'XL':
        this.xlcolor = 'gray';
        break;
      case 'x9':
        this.x9color = 'gray';
        break;
      case 'x19':
        this.x19color = 'gray';
        break;
    }
  }

  resetSelection(): void {
    this.selected = false;
    this.selection = '';

    this.scolor = 'white';
    this.mcolor = 'white';
    this.lcolor = 'white';
    this.xlcolor = 'white';
    this.x9color = 'white';
    this.x19color = 'white';
  }

  itemsSize() {
    return this.items.length;
  }
}
