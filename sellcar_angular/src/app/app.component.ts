import { Component } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'sellcar_angular';

  isAdminLoggedIn:Boolean = StorageService.isAdminLoggedIn();
  isCustomerLoggedIn:Boolean = StorageService.isCustomerLoggedIn();

  constructor(private router: Router){ }

  ngOnInit(){
    this.router.events.subscribe(event => {
      if(event.constructor.name === "NavigationEnd"){
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
      }
    })

  }

  logout(){
    StorageService.signout();
    this.router.navigateByUrl("/login");
  }
}
