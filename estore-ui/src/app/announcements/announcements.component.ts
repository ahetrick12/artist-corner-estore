import { Component, Inject, OnInit } from '@angular/core';
import { AnnouncementsService } from '../announcements.service';

import { Announcement } from '../announcement';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css']
})
export class AnnouncementsComponent implements OnInit {
  title: string = '';
  message: string = '';
  announcements: Announcement[] = [];

  constructor(private announcementsService: AnnouncementsService) { }

  ngOnInit(): void {
    this.getAnnouncements()
  }

  getAnnouncements(): void {
    this.announcementsService.getAnnouncements()
    .subscribe(announcments => this.announcements = announcments.reverse());
  }

  onSubmit() {
    const announcement: Announcement = {
      id: 0,
      title: this.title,
      message: this.message
    }

    this.announcementsService.addAnnouncement(announcement).subscribe(() => {
      this.announcementsService.getAnnouncements();
      this.getAnnouncements();
    })
  }

  onDelete(announcement: Announcement) {
    console.log("delete");

    this.announcementsService.deleteAnnouncement(announcement).subscribe(() => {
      this.announcementsService.getAnnouncements();
      this.getAnnouncements();
    })
  }
}
