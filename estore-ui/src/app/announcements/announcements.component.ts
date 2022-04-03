import { Component, OnInit } from '@angular/core';
import { AnnouncementsService } from '../announcements.service';

import { Announcement } from '../announcement';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css']
})
export class AnnouncementsComponent implements OnInit {

  constructor(private announcementsService: AnnouncementsService, private announcement: Announcement) { }

  ngOnInit(): void {
  }

  onSubmit() {
      this.announcement.id = 0;
      this.announcement.title = "Test";
      this.announcement.message = "Test";

      this.announcementsService.addAnnouncement(this.announcement).subscribe(() => {
        this.announcementsService.getAnnouncements();
      })
  }
}
