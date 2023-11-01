import { Test } from './../details/test';
import { AdminService } from './../service/admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private adminService:AdminService) { 
     this.getAllTests;
  }

  test !:Test[];

  ngOnInit(): void {
    
  }

  getAllTests()
  {
    this.adminService.getAllTest().subscribe(data=>{
        this.test = data;
    },
    err=>
    {
      console.log(err.error);
    }
    
    ); 
  }
}
