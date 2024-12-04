import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from 'src/app/auth/services/storage/storage.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.scss']
})
export class UpdateCarComponent {

  id:number = this.activatedRoute.snapshot.params["id"];
  existingImage: string | null = null;
  listOfBrands = ["BMW", "AUDI", "FERRARI", "MERCEDES-BENZ", "TESLA", "TOYOTA", "HONDA", "FORD", "CHEVROLET", "VOLKSWAGEN", "NISSAN", "HYUNDAI", "KIA", "MAHINDRA", "TATA MOTORS", "MARUTI SUZUKI", "HINDUSTAN MOTORS", "ASHOK LEYLAND", "RENAULT", "SKODA", "LAMBORGHINI", "PORSCHE", "BUGATTI", "JAGUAR", "LAND ROVER"];
  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automatic"];
  updateCarForm: FormGroup;
  isSpinning: boolean=false;
  imagePreview: string | ArrayBuffer | null;
  selectedFile: File | null;
  imageChanged:boolean=false;

  constructor(private service:CustomerService,
    private activatedRoute:ActivatedRoute,
    private fb:FormBuilder,
    private router:Router,
    private message: NzMessageService
  ) { }

  ngOnInit(){
    this.updateCarForm = this.fb.group({
      name:[null,Validators.required],
      brand:[null,Validators.required],
      type:[null,Validators.required],
      transmission:[null,Validators.required],
      color:[null,Validators.required],
      year:[null,Validators.required],
      price:[null,Validators.required],
      description:[null,Validators.required],
    });
    this.getCar();
  };

  getCar(){
    this.service.getCarById(this.id).subscribe((res) => {
      console.log(res);
      this.existingImage= 'data:image/jpeg;base64,' + res.returnedImg;
      this.updateCarForm.patchValue(res);
    })
  }

  updateCar(){
    this.isSpinning = true;
    const formData:FormData=new FormData();
    formData.append("img",this.selectedFile);
    formData.append("brand", this.updateCarForm.get('brand').value);
    formData.append("name", this.updateCarForm.get('name').value);
    formData.append("type", this.updateCarForm.get('type').value);
    formData.append("color", this.updateCarForm.get('color').value);
    formData.append("model", this.updateCarForm.get('year').value);
    formData.append("transmission", this.updateCarForm.get('transmission').value);
    formData.append("description", this.updateCarForm.get('description').value);
    formData.append("price", this.updateCarForm.get('price').value);
    formData.append("userId", StorageService.getUserId());
    this.service.updateCar(this.id, formData).subscribe((res) => {
      this.isSpinning=false;
      this.message.success("Car Updated Succesfully", {nzDuration:5000});
      this.router.navigateByUrl("/customer/dashboard")
    }, error=> {
      this.isSpinning=false;
      this.message.error("Something went wrong", { nzDuration: 5000})
    })

  }

  onFileSelected(event:any){
    console.log(event.target.files)
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imageChanged = true;
    this.existingImage=null;
  }

  previewImage(){
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }


}
