import { Component } from '@angular/core';
import { Medicine } from 'src/app/interfaces/Medicine.interface';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-user-browse-medicine',
  templateUrl: './user-browse-medicine.component.html',
  styleUrls: ['./user-browse-medicine.component.css'],
})
export class UserBrowseMedicineComponent {
  constructor(private medicineService: MedicineService) {
    this.fetchMedcines();
  }
  medicines: Medicine[];

  fetchMedcines() {
    this.medicineService
      .getMedicineDetails()
      .subscribe((medicines: Medicine[]) => {
        this.medicines = medicines.filter((medi) => medi.active);
      });
  }
}
