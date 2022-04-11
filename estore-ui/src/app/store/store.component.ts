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
  modal = 0;
  displayNone = "none";
  size='';
  scolor = "white";
  mcolor = "white";
  lcolor = "white";
  xlcolor = "white";
  x9color = "white";
  x19color = "white";
  showSizes = "none";
  showFrames = "none";
  itemid = -1;
  itemName = "";
  itemPrice = 0.0;

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
    //console.log(this.items.length);
  }

  add(item: Item): void {
    this.cartService
      .addCartItem(this.authService.getCurrentUser().username, item)
      .subscribe();
  }

  adminLoggedIn(): boolean {
    return this.authService.userIsAdmin();
  }

  viewMore(item: Item){
    this.itemid = item.id;
    this.itemName = item.name;
    this.itemPrice = item.price;
    this.displayNone = "Block";
    if (item.name.includes("Print")){
      this.showFrames = "Block";
      this.showSizes = "none";
    } else{
      this.showSizes = "Block";
      this.showFrames = "none";
    }
  }

  viewLess(){
    this.displayNone = "none";
  }

  select(str: string){
    this.size=str;
    if (str == "S"){
      if (this.scolor == "white"){
        this.scolor="green";
      }else{
        this.scolor="white";
      }
      this.mcolor="white";
      this.lcolor="white";
      this.xlcolor="white";
    }
    if (str == "M"){
      if (this.mcolor == "white"){
        this.mcolor="green";
      }else{
        this.mcolor="white";
      }
      this.scolor="white";
      this.lcolor="white";
      this.xlcolor="white";
    }
    if (str == "L"){
      if (this.lcolor == "white"){
        this.lcolor="green";
      }else{
        this.lcolor="white";
      }
      this.mcolor="white";
      this.scolor="white";
      this.xlcolor="white";
    }
    if (str == "XL"){
      if (this.xlcolor == "white"){
        this.xlcolor="green";
      }else{
        this.xlcolor="white";
      }
      this.mcolor="white";
      this.lcolor="white";
      this.scolor="white";
    }
    if (str=="x9"){
      if (this.x9color == "white"){
        this.x9color = "green";
        //this.x19color = "white";
      } else{
        this.x9color = "white";
        //this.x19color = "white";
      }
      this.x19color = "white";
    }
    else if (str="x19"){
      if (this.x19color == "white"){
        this.x19color = "green";
        //this.x9color = "white";
      } else{
        this.x19color = "white";
        //this.x9color = "white";
      }
      this.x9color="white"
    }
  }
}

