import { Component, OnInit } from '@angular/core';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  search: string = '';


  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    console.log("Hello");
    
  }

  onSubmit() {
    this.itemService.searchItem(this.search);
    console.log("test");
  }

}
