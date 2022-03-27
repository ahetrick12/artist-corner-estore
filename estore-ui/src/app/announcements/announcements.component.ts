import { Component, OnInit } from '@angular/core';
import { AnnouncementsService } from '../announcements.service';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css']
})
export class AnnouncementsComponent implements OnInit {

  constructor(private announcementsService: AnnouncementsService) { }

  ngOnInit(): void {
  }

  onSubmit() {

  }
}
