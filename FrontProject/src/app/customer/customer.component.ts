import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent {
  employeeArray : any[] = [];

  
  firstName: string ="";
  lastName: string ="";
  address: string ="";
  mobile: Number =0;
  currentid = "";


  constructor(private http: HttpClient){
this.getAllEmployee();

}


getAllEmployee()
{
  
  this.http.get("http://localhost:8080/api/v1/employee/getAllEmployee")

  .subscribe((resultData: any)=>
  {
     
      console.log(resultData);
      this.employeeArray = resultData;
  });
}
  
register()
{

  let bodyData = {
    "firstName" : this.firstName,
    "lastName" : this.lastName,
    "address" : this.address,
    "mobile" : this.mobile
  };
  this.http.post("http://localhost:8080/api/v1/employee/save",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
  {
      console.log(resultData);
      Swal.fire(
        'Good job!',
        'was successfully!',
        'success'
      )
      this.getAllEmployee();

      this.firstName = '';
    this.lastName = '';
    this.address = '';
      this.mobile  = 0;
  });
}

clear()
{


      this.firstName = '';
    this.lastName = '';
    this.address = '';
      this.mobile  = 0;
}





setUpdate(data: any)
{
 this.firstName = data.firstName;
 this.lastName = data.lastName;
 this.address = data.address;
 this.mobile = data.mobile;
 this.currentid = data.id;
}
UpdateRecords()
{
  let bodyData = {
    "id" : this.currentid,
    "firstName" : this.firstName,
    "lastName" : this.lastName,
    "address":this.address,
    "mobile" : this.mobile
  };
  
  this.http.put("http://localhost:8080/api/v1/employee/update",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
  {
      console.log(resultData);
      Swal.fire(
        'Good job!',
        'was Update!',
        'success'
      )
      this.getAllEmployee();
      this.firstName = '';
      this.lastName = '';
      this.address = '';
        this.mobile  = 0;
  });
}
save()
{
  if(this.currentid == '')
  {
      this.register();
  }
    else
    {
     this.UpdateRecords();
    }      
}
setDelete(data: any)
{
  
  
  this.http.delete("http://localhost:8080/api/v1/employee/delete"+ "/"+ data.id,{responseType: 'text'}).subscribe((resultData: any)=>
  {
      console.log(resultData);
      Swal.fire(
        'Good job!',
        'was Deleted!',
        'success'
      )
      this.getAllEmployee();

      this.firstName = '';
      this.lastName = '';
      this.address = '';
        this.mobile  = 0;

  });
}


}
