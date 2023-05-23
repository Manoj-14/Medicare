import { Component } from '@angular/core';
import { Admin } from 'src/app/interfaces/Admin.interface';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  email: String = sessionStorage.getItem('adminEmail');
  constructor(private adminService: AdminService) {
    this.fetchAdmin(this.email);
  }

  admin: Admin;

  fetchAdmin(email) {
    this.adminService.getAdminDashboard(email).subscribe((admin: Admin) => {
      this.admin = admin;
    });
  }
}
