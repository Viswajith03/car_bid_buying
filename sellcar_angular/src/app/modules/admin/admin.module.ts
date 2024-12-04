import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';
import { SearchCarComponent } from './components/search-car/search-car.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    GetBookingsComponent,
    SearchCarComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class AdminModule { }
