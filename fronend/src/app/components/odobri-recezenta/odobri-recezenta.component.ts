import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-odobri-recezenta',
  templateUrl: './odobri-recezenta.component.html',
  styleUrls: ['./odobri-recezenta.component.css']
})
export class OdobriRecezentaComponent implements OnInit {

  constructor(private userService:UserService, private activatedRoute:ActivatedRoute) { }
  
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  id = this.activatedRoute.snapshot.params.taskId;
  email:string;

  ngOnInit() {
    this.userService.getTaskById(this.id).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
      this.formFields.forEach(element => {
        if(element.id === "email_kor"){
          this.email = element.defaultValue;
        }
      });
    })
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }
    o.push({fieldId : "email_kor", fieldValue : this.email});
    console.log(o);
    this.userService.odobriRecezenta(o,this.id).subscribe(res=>{
      window.location.href="/mojiTaskovi";
    })
  }

}
