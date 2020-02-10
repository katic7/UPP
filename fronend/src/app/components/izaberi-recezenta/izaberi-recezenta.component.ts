import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { RadService } from 'src/app/services/rad.service';
import { ActivatedRoute } from '@angular/router';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-izaberi-recezenta',
  templateUrl: './izaberi-recezenta.component.html',
  styleUrls: ['./izaberi-recezenta.component.css']
})
export class IzaberiRecezentaComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  id = this.activatedRoute.snapshot.params.id;
  private lista = [];
  private selectedItems = [];
  private dropDownList = [];
  dropdownSettings:IDropdownSettings;

  constructor(private userService:UserService, private radService:RadService, private activatedRoute:ActivatedRoute) {
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
    this.userService.getTaskById(this.id).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
      this.formFields.forEach(element => {
        if(element.id == "recezenti"){
          this.lista = Object.entries(element.type.values);
          this.lista.forEach(ele => {
            let here = { item_id: ele[0], item_text: ele[1]};
            this.dropDownList.push(here);
          });
        }
      });
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
    let o = new Array();
    console.log(value);
    if(this.selectedItems.length < 2){
      alert("Morate odabrati najmanje 2 recezenta");
    }else if(value.rokH==0 && value.rokD==0 && value.rokM==0){
      alert("Morate uneti validan vremenski rok")
    }else{
      for(var property in value){
        if(property != "recezenti"){
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
      this.radService.setRecezente(o,this.formFieldsDto.taskId).subscribe(data=>{
        window.location.href="/mojiTaskovi";
      })
    }
  }

}
