import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { CasopisService } from 'src/app/services/casopis.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  private repeated_password = "";
  private categories = [];
  private formFieldsDto = null;
  private formFields = [];
  private choosen_category = -1;
  private processInstance = "";
  private enumValues = [];
  private tasks = [];
  private email: string;
  dropdownSettings:IDropdownSettings;
  private selectedItems = [];
  private dropDownList = [];

  constructor(private userService: UserService, private router: Router, private casopisService:CasopisService) { 
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      allowSearchFilter: true,

    };
  }

  ngOnInit() {
    this.casopisService.getNaucneOblasti().subscribe(res=>{
      let here = res;
      console.log(res);
      here.forEach(element => {
        let prom = {item_id:element.id, item_text:element.naziv};
        this.dropDownList.push(prom);
      });
    })
    this.userService.startProcess("registracija").subscribe(data=>{
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
    })
  }

  onItemSelect(item: any, a: any) {
      this.selectedItems.push(item); 
  }
  onSelectAll(items: any, a: any) {
      this.selectedItems.push(items);
  }
  onItemDeSelect(item: any, a: any) {
      this.selectedItems.splice(item,1);
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      if (property=="email") {
        this.email = value[property];
      }
      if (property!="naucne_oblasti") {
        o.push({fieldId : property, fieldValue : value[property]});
      }else{
        let all = [];
          value[property].forEach(element => {
              all.push(element.item_id);
          });
        o.push({fieldId: property, enumi: all});
      }
    }
    o.push({fieldId:"email", fieldValue:this.email})
    console.log(o);
    this.userService.registerUser(o, this.formFieldsDto.taskId).subscribe(res=>{
      console.log(res);
      alert("You registered successfully!")
      this.router.navigate(['/verifikujProfil/' + this.email + '/' + this.processInstance]);
        
      },
      err => {
        console.log("Error occured");
    });
  }

}

