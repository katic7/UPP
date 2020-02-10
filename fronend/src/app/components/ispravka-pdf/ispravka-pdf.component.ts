import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { RadService } from 'src/app/services/rad.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ispravka-pdf',
  templateUrl: './ispravka-pdf.component.html',
  styleUrls: ['./ispravka-pdf.component.css']
})
export class IspravkaPdfComponent implements OnInit {

  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  id = this.activatedRoute.snapshot.params.id;
  fileUrl: string;
  fileToUpload: File;

  constructor(private activatedRoute:ActivatedRoute, private userService:UserService, private radService:RadService) { }

  ngOnInit() {
    this.userService.getTaskById(this.id).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
    })
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

onSubmit(value,form){
  let o = new Array();
  this.radService.uploadDocument(this.fileToUpload).subscribe(data=>{
    //console.log(data + "SDADSADASD")
    o.push({fieldId : "ispravka_pdfa", fieldValue : data});
    for (var property in value) {
      if (property!="ispravka_pdfa") {
        o.push({fieldId : property, fieldValue : value[property]});
      }
    }
    this.radService.ispraviPdf(o,this.formFieldsDto.taskId).subscribe(res=>{
      window.location.href="/mojiTaskovi";
    })
    console.log(o);
});

}

}
