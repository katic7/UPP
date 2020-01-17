import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { CasopisService } from 'src/app/services/casopis.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-kreiraj-casopis',
  templateUrl: './kreiraj-casopis.component.html',
  styleUrls: ['./kreiraj-casopis.component.css']
})
export class KreirajCasopisComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private selectedItems = [];
  private selectedItemsPayment = [];
  private dropDownList = [];
  private dropDownPlacanje=[];
  dropdownSettings:IDropdownSettings;
  issn:string;

  constructor(private userService:UserService, private casopisService:CasopisService) {

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

    this.casopisService.getNacinePlacanja().subscribe(res=>{
      let here = res;
      here.forEach(element => {
        let prom = {item_id:element.id, item_text:element.nacin_placanja};
        this.dropDownPlacanje.push(prom);
      });
    })
    this.casopisService.kreirajCasopis("kreiranje_casopisa").subscribe(data=>{
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
    })
  }

  onItemSelect(item: any, a: any) {
    if (a == 'payment') {
      this.selectedItemsPayment.push(item);
    }else{
      this.selectedItems.push(item);
    }
    
  }
  onSelectAll(items: any, a: any) {
    if (a == 'payment') {
      this.selectedItemsPayment.push(items);
    }else{
      this.selectedItems.push(items);
    }
  }
  onItemDeSelect(item: any, a: any) {
    if (a == 'payment') {
      this.selectedItemsPayment.splice(item,1);
    }else{
      this.selectedItems.splice(item,1);
    }
    
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      if (property!="naucne_oblasti" && property!="naplata") {
        o.push({fieldId : property, fieldValue : value[property]});
      }else{
        if(value[property] != ""){
          let all = [];
            value[property].forEach(element => {
                all.push(element.item_id);
            });
          o.push({fieldId: property, enumi: all});
        }
        
    }
    if(property =="issn"){
      this.issn=value[property];
    }
    }

    console.log(o);
    this.casopisService.napraviCasopis(o, this.formFieldsDto.taskId).subscribe(res=>{
      console.log(res);
      alert("Uspesno kreiran casopis!")
      window.location.href="/odabirUrednika/"+this.processInstance+"/"+this.issn;
      },
      err => {
        console.log("Error occured");
    });
  }

}
