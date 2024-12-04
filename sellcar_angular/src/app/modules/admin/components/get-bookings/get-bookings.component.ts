import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrls: ['./get-bookings.component.scss']
})
export class GetBookingsComponent {
  bids: any = [];
  isSpinning: boolean =false;

  constructor(private service:AdminService,
    private message:NzMessageService
  ) { }

  ngOnInit(){
    this.getMyBids();
  };

  getMyBids(){
    this.isSpinning=true;
    this.service.getBids().subscribe((res) => {
      this.isSpinning=false;
      console.log(res);
      this.bids=res;
    })
  }


}
