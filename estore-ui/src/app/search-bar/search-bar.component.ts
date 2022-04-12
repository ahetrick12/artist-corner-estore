import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  @Output() getItems: EventEmitter<any> = new EventEmitter();
  search: string = '';


  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.itemService.searchItem(this.search);
    this.getItems.emit(); //calls getItems from the store component to refresh the list
  }

}
