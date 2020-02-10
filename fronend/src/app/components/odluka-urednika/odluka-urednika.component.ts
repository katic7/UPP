import { Component, OnInit } from '@angular/core';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { UserService } from 'src/app/services/user.service';
import { RadService } from 'src/app/services/rad.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-odluka-urednika',
  templateUrl: './odluka-urednika.component.html',
  styleUrls: ['./odluka-urednika.component.css']
})
export class OdlukaUrednikaComponent implements OnInit {

  
  private processId = this.activatedRoute.snapshot.params.id;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private selectedItems = [];
  private dropDownList = [];
  private lista = [];
  
  dropdownSettings:IDropdownSettings;

  constructor(private userService:UserService, private radService:RadService,private router:Router, private activatedRoute:ActivatedRoute) { 
    this.dropdownSettings = {
      singleSelection: true,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      allowSearchFilter: true,

    };
  }
  ngOnInit() {
    this.userService.getUserTask2(this.processId).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
      this.formFields.forEach(element => {
        if(element.id == "prikladan_ured"){
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
  onSubmit(value, form){
    let o = new Array();
    console.log(value)
    if(this.selectedItems.length == 0 || value.komentar_rada == ''|| value.komentar_uredniku==''){
      alert("Molimo Vas da unesete sva polja");
    }else{
      for(var property in value){
        if(property == "ispravakD" || property == "ispravakH" || property == "ispravakM"){
          o.push({fieldId : property, fieldValue : value[property]});
        }else if(property == "prikladan_ured"){
          if(value[property] != ""){
            let all = [];
              value[property].forEach(element => {
                  all.push(element.item_id);
              });
            o.push({fieldId: property, enumi: all});
          }
        }
      }
      this.radService.urednikPreglda(o,this.formFieldsDto.taskId).subscribe(data=>{
        window.location.href="/mojiTaskovi";
      })
    }
  }

}
