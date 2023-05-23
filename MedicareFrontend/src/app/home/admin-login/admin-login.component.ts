import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Admin } from 'src/app/interfaces/Admin.interface';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css'],
})
export class AdminLoginComponent {
  constructor(private adminService: AdminService, private router: Router) {}

  isError: boolean = false;

  onAdminLogin(admin: Admin, form: NgForm) {
    this.adminService.authenticateAdmin(admin).subscribe(
      (dbAdmin: Admin) => {
        this.router.navigate(['admin/profile']);
        sessionStorage.setItem('adminEmail', admin.email.toString());
      },
      (err) => {
        this.isError = true;
        setTimeout(() => {
          this.isError = false;
        }, 5000);
      }
    );
    form.reset();
  }
}
