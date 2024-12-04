import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { PostCarComponent } from './components/post-car/post-car.component';
import { DemoNgZorroAntdModule } from 'src/app/DemoNgZorroAntdModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MyCarsComponent } from './components/my-cars/my-cars.component';
import { UpdateCarComponent } from './components/update-car/update-car.component';
import { BookACarComponent } from './components/book-a-car/book-a-car.component';
import { ViewMyBidsComponent } from './components/view-my-bids/view-my-bids.component';
import { ManageBidsComponent } from './components/manage-bids/manage-bids.component';
import { SearchCarComponent } from './components/search-car/search-car.component';


@NgModule({
  declarations: [
    CustomerDashboardComponent,
    PostCarComponent,
    MyCarsComponent,
    UpdateCarComponent,
    BookACarComponent,
    ViewMyBidsComponent,
    ManageBidsComponent,
    SearchCarComponent,
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule

  ]
})
export class CustomerModule { }
