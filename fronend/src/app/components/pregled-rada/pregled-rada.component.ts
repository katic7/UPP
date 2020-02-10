import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { RadService } from 'src/app/services/rad.service';

@Component({
  selector: 'app-pregled-rada',
  templateUrl: './pregled-rada.component.html',
  styleUrls: ['./pregled-rada.component.css']
})
export class PregledRadaComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  id = this.activatedRoute.snapshot.params.id;

  constructor(private activatedRoute:ActivatedRoute, private userService:UserService, private radService:RadService) { }

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
    this.radService.pregledRada(o,this.id).subscribe(res=>{
      window.location.href="/mojiTaskovi";
    })
  }

}
