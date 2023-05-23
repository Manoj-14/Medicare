import { Component, OnInit } from '@angular/core';
import { MedicineService } from '../services/medicine.service';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers: [MedicineService, AdminService],
})
export class AdminComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
