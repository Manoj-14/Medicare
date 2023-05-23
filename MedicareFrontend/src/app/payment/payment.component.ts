import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent {
  @Input()
  total: number;

  @Input()
  quantity: number;

  @Input()
  offer: number;

  @Input()
  subtotal: number;
}
