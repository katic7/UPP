import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { CasopisService } from 'src/app/services/casopis.service';

@Component({
  selector: 'app-odobri-casopis',
  templateUrl: './odobri-casopis.component.html',
  styleUrls: ['./odobri-casopis.component.css']
})
export class OdobriCasopisComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  id = this.activatedRoute.snapshot.params.taskId;

  constructor(private userService:UserService, private activatedRoute:ActivatedRoute, private casopisService:CasopisService) { }

  ngOnInit() {
    this.userService.getTaskById(this.id).subscribe(data=>{
      console.log(data);
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
      o.push({fieldId : property, fieldValue : value[property]});
    }
    console.log(o);
    this.casopisService.pregledCas(o,this.id).subscribe(res=>{
      window.location.href="/mojiTaskovi";
    })
  }
}
