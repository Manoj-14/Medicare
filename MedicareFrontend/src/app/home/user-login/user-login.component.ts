import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/User.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
})
export class UserLoginComponent {
  constructor(private userService: UserService, private router: Router) {}

  isError: boolean = false;

  onUserLogin(user: User, form: NgForm) {
    this.userService.authenticateUser(user).subscribe(
      (dbUser: User) => {
        sessionStorage.setItem('userEmail', dbUser.email.toString());
        this.router.navigate(['user/profile']);
      },
      (err) => {
        this.isError = true;
        setTimeout(() => {
          this.isError = false;
        }, 5000);
      }
    );
    form.reset();
  }
}
