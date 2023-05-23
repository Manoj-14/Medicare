import { Component } from '@angular/core';
import { MedicineService } from '../services/medicine.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [MedicineService],
})
export class UserComponent {
  main: boolean = true;
}
