import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin } from '../interfaces/Admin.interface';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private httpClient: HttpClient) {}

  url: String = 'http://localhost:8080/api';

  authenticateAdmin(admin: Admin) {
    return this.httpClient.post(`${this.url}/admin/login`, admin);
  }
  getAdminDashboard(email: String) {
    return this.httpClient.post(`${this.url}/admin/dashboard`, email);
  }
}
