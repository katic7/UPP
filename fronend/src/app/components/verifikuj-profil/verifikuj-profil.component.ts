import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-verifikuj-profil',
  templateUrl: './verifikuj-profil.component.html',
  styleUrls: ['./verifikuj-profil.component.css']
})
export class VerifikujProfilComponent implements OnInit {

  constructor(private router:Router, private activatedRoute: ActivatedRoute, private userService: UserService) { }
  
  private formFieldsDto = null;
  private formFields = [];
  private processInstance = "";
  private processId = this.activatedRoute.snapshot.params.pid;
  private email = this.activatedRoute.snapshot.params.email;

  ngOnInit() {
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
      o.push({fieldId : property, fieldValue : value[property]});
    }
    o.push({fieldId: "email",fieldValue: this.email});
    console.log(o);
    this.userService.aktivacijaKor(o, this.formFieldsDto.taskId).subscribe(res=>{
      console.log(res);
      alert("You registered successfully!")
      this.router.navigate(['logovanje']);
        
      },
      err => {
        console.log("Error occured");
    });
  }

}
