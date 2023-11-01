import { Test } from './../details/test';
import { Admin } from './../details/admin';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  private address = 'http://localhost:9999';
  private createAdmin_endpoint = this.address+'/admin/add';
  private getAllTest_endpoint = this.address+'/test/viewAll';

  getAllTest():Observable<Test[]>
  {
    return this.http.get<Test[]>(`${this.getAllTest_endpoint}`);
  }

  createAdmin(admin:Admin):Observable<Object>
  {
    return this.http.post<Admin>(`${this.createAdmin_endpoint}`,admin);
  }
}
