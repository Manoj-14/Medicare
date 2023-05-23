import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Medicine } from 'src/app/interfaces/Medicine.interface';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-edit-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css'],
})
export class EditMedicineComponent {
  constructor(private medicineService: MedicineService) {}
  @Input()
  medicine: Medicine;

  @Output()
  update = new EventEmitter<{ update: boolean }>();

  @Output()
  medicineObj = new EventEmitter<any>();

  edit() {
    this.medicineObj.emit(this.medicine);
  }
  activeBtn() {
    this.medicineService.enableOrDisable(this.medicine.id).subscribe((resp) => {
      this.update.emit({ update: true });
    });
  }
}
