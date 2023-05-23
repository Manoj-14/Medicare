import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Cart } from 'src/app/interfaces/Cart.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  constructor(private userService: UserService) {}

  email: string = sessionStorage.getItem('userEmail');

  isError: boolean;
  isSuccess: boolean;
  message: string;

  @Input()
  cart: Cart;

  @Output()
  update = new EventEmitter<{ update: boolean }>();

  ngOnInit(): void {}

  increQuantity() {
    this.userService
      .addToCart(this.email, this.cart.medicines.id, this.cart.quantity)
      .subscribe((resp) => {
        this.update.emit({ update: true });
      });
  }
  decreQuantity() {
    this.userService
      .removeFromCart(this.email, this.cart.medicines.id)
      .subscribe((resp) => {
        this.update.emit({ update: true });
      });
  }
}
