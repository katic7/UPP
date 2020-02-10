import { Component, OnInit } from '@angular/core';
import { CasopisService } from 'src/app/services/casopis.service';
import { Router } from '@angular/router';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { AuthService } from 'src/app/auth/service/auth.service';
import { RadService } from 'src/app/services/rad.service';

@Component({
  selector: 'app-odabir-casopisa',
  templateUrl: './odabir-casopisa.component.html',
  styleUrls: ['./odabir-casopisa.component.css']
})
export class OdabirCasopisaComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private selectedItems = [];
  private dropDownList = [];
  
  dropdownSettings:IDropdownSettings;
  
  constructor(private casopisService:CasopisService, private router:Router, private authService:AuthService, private radService:RadService) { 
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
    this.casopisService.getAll().subscribe(res=>{
      let here = res;
      console.log(res);
      here.forEach(element => {
        let prom = {item_id:element.id, item_text:element.naziv};
        this.dropDownList.push(prom);
      });
    })
    
    this.authService.getLogged().subscribe(res=>{
      this.casopisService.startKreirajRad("obrada_teksta",res.username).subscribe(data=>{ 
        this.formFieldsDto = data;
        this.formFields = data.formFields;
        this.processInstance = data.processInstanceId;
      })
    })
    
  }

  onItemSelect(item: any, a: any) {
    if (a == 'payment') {
      this.selectedItems.push(item);
    }else{
      this.selectedItems.push(item);
    }
    
  }
  onSelectAll(items: any, a: any) {
    if (a == 'payment') {
      this.selectedItems.push(items);
    }else{
      this.selectedItems.push(items);
    }
  }
  onItemDeSelect(item: any, a: any) {
    if (a == 'payment') {
      this.selectedItems.splice(item,1);
    }else{
      this.selectedItems.splice(item,1);
    }
    
  }

  onSubmit(value,form){
    if(this.selectedItems.length != 1){
      alert("Morate odabrati tacno jedan casopis");
    }else{
      let o = new Array();
      for (var property in value) {
        let all = [];
            value[property].forEach(element => {
                all.push(element.item_id);
            });
          o.push({fieldId: property, enumi: all});
          console.log(all);
        }
        
        this.casopisService.odabirCasopisa(o,this.formFieldsDto.taskId).subscribe(data=>{
          this.authService.getLogged().subscribe(res=>{
            this.radService.setAutora(this.processInstance, res.id).subscribe(rez=>{
              alert("Uspesno odabran casopis!");
              this.router.navigate(['kreiranjeRada/' + this.processInstance]);
            })
          })
          
        })
      }
    }
    
  

}
