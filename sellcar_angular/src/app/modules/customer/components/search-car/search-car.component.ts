import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrls: ['./search-car.component.scss']
})
export class SearchCarComponent implements OnInit {

  listOfBrands = ["BMW", "AUDI", "FERRARI", "MERCEDES-BENZ", "TESLA", "TOYOTA", "HONDA", "FORD", "CHEVROLET", "VOLKSWAGEN", "NISSAN", "HYUNDAI", "KIA", "MAHINDRA", "TATA MOTORS", "MARUTI SUZUKI", "HINDUSTAN MOTORS", "ASHOK LEYLAND", "RENAULT", "SKODA", "LAMBORGHINI", "PORSCHE", "BUGATTI", "JAGUAR", "LAND ROVER"];

  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];

  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];

  listOfTransmission = ["Manual", "Automatic"];

  searchCarForm: FormGroup;
  isSpinning: boolean=false;
  cars:any[]=[];

  constructor(private service:CustomerService,
    private fb:FormBuilder,
  ){ }

  ngOnInit(){
    this.searchCarForm = this.fb.group({
      brand:[null],
      type:[null],
      transmission:[null],
      color:[null],
    })
  };

  searchCar(){
    this.isSpinning=true;
    this.cars=[];
    this.service.searchCar(this.searchCarForm.value).subscribe((res)=>{
      this.isSpinning=false;
      this.cars=res;
    })
    console.log(this.searchCarForm.value);
  }

}
