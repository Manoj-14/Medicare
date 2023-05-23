import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Medicine } from 'src/app/interfaces/Medicine.interface';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css'],
})
export class AddMedicineComponent {
  constructor(private medicineService: MedicineService) {
    this.fetchMedcines();
    console.log(this.medicines);
  }
  medicineAdded: boolean = false;
  name: String;
  email: string = sessionStorage.getItem('adminEmail');

  dbMedicine: Medicine = {
    id: null,
    name: '',
    price: 0,
    seller: undefined,
    description: undefined,
    active: false,
  };

  onMedicineAdd(medicine: Medicine, form: NgForm) {
    this.medicineService.addMedicine(medicine).subscribe((resp) => {
      this.medicineAdded = true;
      this.name = medicine.name;
      setTimeout(() => {
        this.medicineAdded = false;
      }, 3000);
    });
    form.reset();
  }

  medicines: Medicine[];

  fetchMedcines() {
    this.medicineService
      .getMedicineDetails()
      .subscribe((medicines: Medicine[]) => {
        this.medicines = medicines;
        console.log(this.medicines);
      });
  }
  updateMedicines() {
    this.fetchMedcines();
  }

  editRequest(event) {
    this.dbMedicine = event;
  }
}
