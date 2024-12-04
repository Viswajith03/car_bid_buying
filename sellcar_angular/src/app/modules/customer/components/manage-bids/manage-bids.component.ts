import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-manage-bids',
  templateUrl: './manage-bids.component.html',
  styleUrls: ['./manage-bids.component.scss']
})
export class ManageBidsComponent  {
  bids: any = [];
  isSpinning: boolean =false;
  carId:number = this.activatedRoute.snapshot.params['id']

  constructor(private service:CustomerService,
    private message:NzMessageService,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit(){
    this.getMyBids();
  };

  getMyBids(){
    this.isSpinning=true;
    this.service.getBidsByCarId(this.carId).subscribe((res) => {
      this.isSpinning=false;
      console.log(res);
      this.bids=res;
    })
  }

  changeBookingStatus(id:number,status:string){
    this.isSpinning=true;
    this.service.updateBidStatus(id,status).subscribe((res) => {
      this.isSpinning=false;
      this.message.success("Bid Status Changes Successfully", {nzDuration:5000});
      this.getMyBids();
    }, error=> {
      this.isSpinning=false;
      this.message.error("Something went wrong", { nzDuration: 5000})
    })
    
  }

}
