import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { Route, RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AdminNavComponent } from './admin/admin-nav/admin-nav.component';
import { AdminLoginComponent } from './home/admin-login/admin-login.component';
import { UserComponent } from './user/user.component';
import { UserNavComponent } from './user/user-nav/user-nav.component';

import { ProfileComponent } from './admin/profile/profile.component';
import { MedicineComponent } from './medicine/medicine.component';
import { EditMedicineComponent } from './admin/add-medicine/medicine/medicine.component';
import { UserLoginComponent } from './home/user-login/user-login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserBrowseMedicineComponent } from './user/user-browse-medicine/user-browse-medicine.component';
import { UserCartComponent } from './user/user-cart/user-cart.component';
import { PaymentComponent } from './payment/payment.component';
import { CartComponent } from './user/cart/cart.component';
import { AddMedicineComponent } from './admin/add-medicine/add-medicine.component';
import { ManageMedicineComponent } from './admin/manage-medicine/manage-medicine.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'loginAdmin', component: AdminLoginComponent },
  { path: 'loginUser', component: UserLoginComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin/dashboard', component: AdminDashboardComponent },
  { path: 'user', component: UserComponent },
  { path: 'user/profile', component: UserProfileComponent },
  { path: 'user/browseMedicines', component: UserBrowseMedicineComponent },
  { path: 'user/cart', component: UserCartComponent },
  { path: 'admin/profile', component: ProfileComponent },
  { path: 'admin/manageMedicine', component: ManageMedicineComponent },
  { path: 'admin/addMedicine', component: AddMedicineComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    HomeComponent,
    AdminDashboardComponent,
    AdminNavComponent,
    AdminLoginComponent,
    UserComponent,
    UserNavComponent,
    ProfileComponent,
    MedicineComponent,
    UserLoginComponent,
    UserProfileComponent,
    UserBrowseMedicineComponent,
    UserCartComponent,
    PaymentComponent,
    CartComponent,
    AddMedicineComponent,
    ManageMedicineComponent,
    EditMedicineComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
