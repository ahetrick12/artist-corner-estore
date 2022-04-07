import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../item';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  @Output() getItems: EventEmitter<any> = new EventEmitter();
  search: string = '';
  searchArray : Observable<Item[]> | undefined;


  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    console.log("Hello");

  }

  onSubmit() {
    this.itemService.searchItem(this.search);
    this.searchArray = this.itemService.getItems();
    this.getItems.emit(); //calls getItems from the store component to refresh the list
  }


}
