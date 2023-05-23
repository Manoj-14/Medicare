import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/User.interface';
import { Medicine } from '../interfaces/Medicine.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  url: String = 'http://localhost:8080/api/user';

  authenticateUser(user: User) {
    return this.httpClient.post(`${this.url}/login`, user);
  }

  purchaseMedicine(email: string, medicine: Medicine) {
    const params = new HttpParams()
      .set('email', email)
      .set('medicine_id', medicine.id)
      .set('quantity', 2)
      .set('amount', medicine.price);
    return this.httpClient.post(`${this.url}/purchaceMedicine`, params);
  }

  dashboard(email: string) {
    const params = new HttpParams().set('email', email);
    return this.httpClient.get(`${this.url}/dashboard`, { params });
  }

  addToCart(email: string, medicineId: number, quantity: number) {
    console.log(email, medicineId, quantity);
    const params = new HttpParams()
      .set('email', email)
      .set('medicine_id', medicineId)
      .set('quantity', quantity);
    return this.httpClient.post(`${this.url}/addToCart`, params);
  }

  removeFromCart(email: string, medicineId: number) {
    const params = new HttpParams()
      .set('email', email)
      .set('medicineId', medicineId);
    return this.httpClient.post(`${this.url}/removeFromCart`, params);
  }
}
