import { Component, Inject, OnInit } from '@angular/core';
import { AnnouncementsService } from '../announcements.service';
import { AuthService } from '../auth.service';

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
  announcementTitleEdit: string = '';
  announcementMessageEdit: string = '';

  constructor(private announcementsService: AnnouncementsService, private authService: AuthService) { }

  ngOnInit(): void {
    this.getAnnouncements()
  }

  getAnnouncements(): void {
    this.announcementsService.getAnnouncements()
    .subscribe(announcments => this.announcements = announcments.reverse());
  }

  getTime(millis: string) {
    var date = new Date(millis);

    return date.toLocaleString();
  }

  getAdminStatus() {
    return this.authService.userIsAdmin();
  }

  onSubmit() {
    if(this.title != "" && this.message != "") {
      const announcement: Announcement = {
        id: 0,
        title: this.title,
        message: this.message,
        edited: false,
        timestamp: "",
        editing: false
      }

      this.announcementsService.addAnnouncement(announcement).subscribe(() => {
        this.announcementsService.getAnnouncements();
        this.getAnnouncements();
      })

      this.title = "";
      this.message = "";
    }
    else {
      alert("Please enter both a title and message.");
    }
  }

  onEdit(announcement: Announcement) {
      announcement.editing = true;

      this.announcementTitleEdit = announcement.title;
      this.announcementMessageEdit = announcement.message;
  }

  onEditSubmit(announcement: Announcement) {
    const updatedAnnouncement: Announcement = {
      id: announcement.id,
      title: this.announcementTitleEdit,
      message: this.announcementMessageEdit,
      edited: true,
      timestamp: announcement.timestamp,
      editing: false
    }

    this.announcementsService.updateAnnouncement(updatedAnnouncement).subscribe(() => {
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
