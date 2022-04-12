import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Item } from '../item';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  items: Item[] = [];

  constructor(
    private itemService: ItemService,
    private authService: AuthService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.getItems();

    // Send back to homepage is user is not admin, like if they try to access it by directly going to "http://localhost:4200/admin"
    if (!this.authService.userIsAdmin()) {
      this.route.navigate(['']);
    }
  }

  getItems(): void {
    this.itemService.getItems().subscribe((items) => (this.items = items));
  }

  saveItem(item: Item): void {
    this.itemService.updateItem(item).subscribe();
  }

  deleteItem(item: Item): void {
    this.items = this.items.filter((i) => i !== item);
    this.itemService.deleteItem(item.id).subscribe();
  }

  addItem(): void {
    // Add
    let name: string = 'New Item';
    let newName: string = name;
    for (
      let i = 1;
      this.items.filter((item) => item.name === newName).length > 0;
      i++
    ) {
      console.log(this.items.filter((item) => item.name === newName));
      newName = name.concat(' ' + i);
    }

    let item: Item = {
      id: 0,
      name: newName,
      stock: 0,
      price: 0,
      s: 0,
      m: 0,
      l: 0,
      xl: 0,
      x920: 0,
      x1930: 0,
      image: '',
    };

    this.itemService.addItem(item).subscribe((i) => {
      this.items.push(i);
    });
  }
}
