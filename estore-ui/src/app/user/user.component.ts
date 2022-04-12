import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  
  user : User = this.authService.getCurrentUser();
  constructor(
    private authService: AuthService,
    private userService: UserService
    ) {}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  getUser(): User{
    return this.authService.getCurrentUser();
  }

  saveUser(user: User): void {
    if(user.imageLink !=""){
      this.userService.updateUser(user).subscribe();
    }
}

}

