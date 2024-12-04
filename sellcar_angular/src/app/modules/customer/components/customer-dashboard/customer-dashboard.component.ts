import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.scss']
})
export class CustomerDashboardComponent {

  cars: any = [];
  analytics:any;

  constructor(private service:CustomerService) { }

  ngOnInit(){
    this.getCars();
    this.getAnalytics();
  };

  getCars(){
    this.service.getAllCars().subscribe((res) => {
      console.log(res);
      this.cars=res;
    });
  }

  getAnalytics(){
    this.service.getAnalytics().subscribe((res) => {
      console.log(res);
      this.analytics=res;
    });
  }

  gridStyle ={
    width: '50%',
    textAlign: 'center'
  }

}
