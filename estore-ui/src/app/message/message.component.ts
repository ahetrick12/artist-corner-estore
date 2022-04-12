import { Component, OnInit } from '@angular/core';

import { Message } from '../message';
import { User } from '../user';
import { MessageService } from '../message.service';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  messages: Message[] = [];
  user: User= {
    username: '',
    password: '',
    cart: [],
  };
  mess: string = '';

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
  onSubmit() {
    if(this.mess != "") {
      const messag: Message = {
        id: this.messages.length,
        name: this.authService.getCurrentUser().username,
        message: this.mess,
        
      }
      console.log(messag);
      this.messageService.addMessage(messag).subscribe(() => {
        
        this.getMessages();
      })

      
      this.mess = "";
    }
    else {
      alert("Please enter both a title and message.");
    }
  }
  add(message: Message): void {
    this.messageService
      .addMessage(message)
      .subscribe();
  }

  userLoggedIn(): boolean {
    return this.authService.userLoggedIn();
  }
}
