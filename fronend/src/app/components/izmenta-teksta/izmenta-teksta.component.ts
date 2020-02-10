import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-izmenta-teksta',
  templateUrl: './izmenta-teksta.component.html',
  styleUrls: ['./izmenta-teksta.component.css']
})
export class IzmentaTekstaComponent implements OnInit {

  private processId = this.activatedRoute.snapshot.params.id;
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";

  constructor(private userService:UserService, private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    this.userService.getUserTask2(this.processId).subscribe(data=>{
      console.log(data);
      this.formFieldsDto = data;
      this.formFields = data.formFields;
      this.processInstance = data.processInstanceId;
    })
  }

}
