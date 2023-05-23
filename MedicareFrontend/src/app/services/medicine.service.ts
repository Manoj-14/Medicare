import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin } from '../interfaces/Admin.interface';
import { Medicine } from '../interfaces/Medicine.interface';

@Injectable({
  providedIn: 'root',
})
export class MedicineService {
  constructor(private httpClient: HttpClient) {}

  url: String = 'http://localhost:8080/api';

  getMedicineDetails() {
    return this.httpClient.get(`${this.url}/medicine/findAll`);
  }

  enableOrDisable(id: number) {
    return this.httpClient.put(`${this.url}/medicine/active/${id}`, {});
  }

  addMedicine(medicine: Medicine) {
    return this.httpClient.post(`${this.url}/medicine/create`, medicine);
  }
  updateMedicine(id: number) {}
  deleteMedicine(id: number) {}
}
