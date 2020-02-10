import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { RadService } from 'src/app/services/rad.service';
import { CasopisService } from 'src/app/services/casopis.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-kreiranje-rada',
  templateUrl: './kreiranje-rada.component.html',
  styleUrls: ['./kreiranje-rada.component.css']
})
export class KreiranjeRadaComponent implements OnInit {
  private processId = this.activatedRoute.snapshot.params.id;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private selectedItems = [];
  private dropDownList = [];
  
  dropdownSettings:IDropdownSettings;

  fileUrl: string;
  fileToUpload: File;

  constructor(private userService:UserService, private radService:RadService,private router:Router,private casopisService:CasopisService, private activatedRoute:ActivatedRoute) { 
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

    this.userService.getUserTask(this.processId).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
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
    this.radService.uploadDocument(this.fileToUpload).subscribe(data=>{
      //console.log(data + "SDADSADASD")
      o.push({fieldId : "pdf", fieldValue : data});
      for (var property in value) {
        if (property!="pdf" && property!="norada") {
          o.push({fieldId : property, fieldValue : value[property]});
        }else if(property=="norada"){
          if(value[property] != ""){
            let all = [];
              value[property].forEach(element => {
                  all.push(element.item_id);
              });
            o.push({fieldId: property, enumi: all});
          }
          
      }
      }
      this.radService.napraviRad(o,this.formFieldsDto.taskId).subscribe(res=>{
        window.location.href="/mojiTaskovi";
      })
      console.log(o);
});
    }
  }
  
  handleFileInput(file:FileList){
    this.fileToUpload =file.item(0);
    var reader = new FileReader();
    //console.log(this.fileToUpload)
    reader.onload=(event:any)=>{
      this.fileUrl =event.target.result;//ovo ne radi za pdf nesto
    }
    reader.readAsDataURL(this.fileToUpload);
    console.log("URL "+this.fileUrl);
    console.log("file "+this.fileToUpload.name);
}
  

}
