import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { CasopisService } from 'src/app/services/casopis.service';

@Component({
  selector: 'app-odabi-urednika',
  templateUrl: './odabi-urednika.component.html',
  styleUrls: ['./odabi-urednika.component.css']
})
export class OdabiUrednikaComponent implements OnInit {

  constructor(private casopisService:CasopisService ,private userService:UserService, private activatedRoute:ActivatedRoute) { 
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      allowSearchFilter: true,

    };
  }
  issn:string;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private processId = this.activatedRoute.snapshot.params.pid;
  private selectedItems = [];
  private selectedItemsPayment = [];
  private dropDownList = [];
  private dropDownPlacanje=[];
  dropdownSettings:IDropdownSettings;

  ngOnInit() {
    this.casopisService.getRecezenti(this.activatedRoute.snapshot.params.issn).subscribe(data=>{
      let here = data;
      console.log(data);
      here.forEach(element => {
        let prom = {item_id:element.id, item_text:element.ime + " " + element.prezime};
        this.dropDownList.push(prom);
      });
    })

    this.casopisService.getUrednik(this.activatedRoute.snapshot.params.issn).subscribe(data=>{
      let here = data;
      here.forEach(element => {
        let prom = {item_id:element.id, item_text:element.ime + " " + element.prezime};
        this.dropDownPlacanje.push(prom);
      });
    })
    this.userService.getUserTask(this.processId).subscribe(data=>{
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
    })
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      if (property!="urednik" && property!="recezenti") {
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
    }
    console.log(o);
    this.casopisService.izaberiUrednika(o,this.formFieldsDto.taskId).subscribe(data=>{
      window.location.href="mojiTaskovi";
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


}
