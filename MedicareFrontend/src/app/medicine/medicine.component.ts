import { Component, Input } from '@angular/core';
import { Medicine } from '../interfaces/Medicine.interface';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css'],
})
export class MedicineComponent {
  @Input()
  medicine: Medicine;

  @Input()
  isCart: boolean = false;

  @Input()
  isCatalog: boolean = false;

  isError: boolean = false;
  isSuccess: boolean = false;
  message: String;

  email: string = sessionStorage.getItem('userEmail');
  constructor(private router: Router, private userService: UserService) {
    if (this.email == null) {
      this.router.navigate(['/loginUser']);
    }
  }

  addToCart() {
    this.userService.addToCart(this.email, this.medicine.id, 1).subscribe(
      (resp) => {
        this.message = 'Added to cart';
        this.isSuccess = true;
        setTimeout(() => {
          this.isSuccess = true;
        }, 3000);
      },
      (err) => {
        this.message = err.error;
        this.isError = true;
        setTimeout(() => {
          this.isError = true;
        }, 3000);
      }
    );
  }
  buyMedicine() {
    this.userService.purchaseMedicine(this.email, this.medicine).subscribe(
      (respData) => {
        this.message = 'purchased';
        this.isSuccess = true;
        setTimeout(() => {
          this.isSuccess = true;
        }, 3000);
      },
      (err) => {
        this.message = err.error;
        this.isError = true;
        setTimeout(() => {
          this.isError = true;
        }, 3000);
      }
    );
  }
}
