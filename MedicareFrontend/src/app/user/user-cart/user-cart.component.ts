import { Component } from '@angular/core';
import { Cart } from 'src/app/interfaces/Cart.interface';
import { User } from 'src/app/interfaces/User.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.css'],
})
export class UserCartComponent {
  email: string = sessionStorage.getItem('userEmail');

  constructor(private userService: UserService) {
    this.fetchPost(this.email);
  }

  carts: Cart[];
  total: number;
  quantity: number;
  offer: number = 22;
  subtotal: number;

  fetchPost(email: string) {
    this.userService.dashboard(email).subscribe((user: User) => {
      this.carts = user.cart;

      let count = 0;
      this.carts.forEach((index) => {
        count += index.medicines.price;
      });
      this.subtotal = count;
      count = 0;

      this.carts.forEach((index) => {
        count += index.quantity;
      });

      this.quantity = count;
    });
  }

  updateCart() {
    console.log('updated');
    this.fetchPost(this.email);
  }
}
