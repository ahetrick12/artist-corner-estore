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
}
