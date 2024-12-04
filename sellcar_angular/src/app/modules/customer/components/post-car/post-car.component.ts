import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../services/customer.service';
import { BooleanInput } from 'ng-zorro-antd/core/types';
import { StorageService } from 'src/app/auth/services/storage/storage.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrls: ['./post-car.component.scss']
})
export class PostCarComponent {

  listOfBrands = ["BMW", "AUDI", "FERRARI", "MERCEDES-BENZ", "TESLA", "TOYOTA", "HONDA", "FORD", "CHEVROLET", "VOLKSWAGEN", "NISSAN", "HYUNDAI", "KIA", "MAHINDRA", "TATA MOTORS", "MARUTI SUZUKI", "HINDUSTAN MOTORS", "ASHOK LEYLAND", "RENAULT", "SKODA", "LAMBORGHINI", "PORSCHE", "BUGATTI", "JAGUAR", "LAND ROVER"];

  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];

  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];

  listOfTransmission = ["Manual", "Automatic"];

  postCarForm: FormGroup;
  isSpinning: boolean=false;
  imagePreview: string | ArrayBuffer | null;
  selectedFile: File | null;

  constructor(private service:CustomerService,
    private fb:FormBuilder,
    private router:Router,
    private message: NzMessageService
  ){ }

  ngOnInit(){
    this.postCarForm = this.fb.group({
      brand:[null,Validators.required],
      name:[null,Validators.required],
      type:[null,Validators.required],
      transmission:[null,Validators.required],
      color:[null,Validators.required],
      year:[null,Validators.required],
      description:[null,Validators.required],
      price:[null,Validators.required],
    })
  };

  postCar(){
    this.isSpinning = true;
    console.log(this.postCarForm.value);
    console.log(this.selectedFile);
    const formData:FormData=new FormData();
    formData.append("img",this.selectedFile);
    formData.append("brand", this.postCarForm.get('brand').value);
    formData.append("name", this.postCarForm.get('name').value);
    formData.append("type", this.postCarForm.get('type').value);
    formData.append("color", this.postCarForm.get('color').value);
    formData.append("model", this.postCarForm.get('year').value);
    formData.append("transmission", this.postCarForm.get('transmission').value);
    formData.append("description", this.postCarForm.get('description').value);
    formData.append("price", this.postCarForm.get('price').value);
    formData.append("userId", StorageService.getUserId());
    this.service.postCar(formData).subscribe((res) => {
      this.isSpinning=false;
      this.message.success("Car Posted Succesfully", {nzDuration:5000});
      this.router.navigateByUrl("/customer/dashboard")
    }, error=> {
      this.message.error("Something went wrong", { nzDuration: 5000})
    })

  }

  onFileSelected(event:any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }



}
