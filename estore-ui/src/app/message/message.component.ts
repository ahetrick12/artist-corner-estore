import { Component, OnInit } from '@angular/core';

import { Message } from '../message';
import { MessageService } from '../message.service';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css'],
})
export class MessageComponent implements OnInit {
  messages: Message[] = [];

  constructor(
    private messageService: MessageService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.getMessages();
  }

  getMessages(): void {
    this.messageService.getMessages().subscribe((messages) => (this.messages = messages));
  }

  add(message: Message): void {
    this.messageService
      .addMessage(message)
      .subscribe();
  }

  adminLoggedIn(): boolean {
    return this.authService.userIsAdmin();
  }
}
