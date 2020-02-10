import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { RadService } from 'src/app/services/rad.service';

@Component({
  selector: 'app-pregled-pdf',
  templateUrl: './pregled-pdf.component.html',
  styleUrls: ['./pregled-pdf.component.css']
})
export class PregledPdfComponent implements OnInit {

  id = this.activatedRoute.snapshot.params.id;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";

  constructor(private activatedRoute:ActivatedRoute, private userService:UserService, private radService:RadService) { }

  ngOnInit() {
    this.userService.getTaskById(this.id).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
    })
  }

  openPdf(val){
    console.log(val);
    window.open(val);
  }

  onSubmit(value, form){
    let o = new Array();
    for (var property in value) {
      console.log(property);
      console.log(value[property]);
      o.push({fieldId : property, fieldValue : value[property]});
    }
    console.log(o);
    this.radService.pregledPdf(o,this.id).subscribe(res=>{
      window.location.href="/mojiTaskovi";
    })
  }

}
