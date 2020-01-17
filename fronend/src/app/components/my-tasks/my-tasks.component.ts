import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-tasks',
  templateUrl: './my-tasks.component.html',
  styleUrls: ['./my-tasks.component.css']
})
export class MyTasksComponent implements OnInit {

  constructor(private userService:UserService) { }
  private tasks =[];
  ngOnInit() {
    this.userService.getMyTasks("demo").subscribe(data=>{
      this.tasks = data;
    })
  }

  resiTask(id,name){
    if(name == "Provera podataka"){
      window.location.href="/odobriCasopis/" + id;
    }else{
      window.location.href="/odobriRecezenta/" + id;
    }
  }

}
