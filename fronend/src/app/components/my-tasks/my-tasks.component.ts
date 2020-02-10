import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/auth/service/auth.service';

@Component({
  selector: 'app-my-tasks',
  templateUrl: './my-tasks.component.html',
  styleUrls: ['./my-tasks.component.css']
})
export class MyTasksComponent implements OnInit {

  constructor(private userService:UserService, private authService:AuthService) { }
  private tasks =[];
  private kor = {};

  ngOnInit() {
    this.authService.getLogged().subscribe(res=>{
      this.kor = res;
      this.userService.getMyTasks(res.username).subscribe(data=>{
        this.tasks = data;
      })  
    })
    
  }

  resiTask(id,name){
    if(name == "Provera podataka"){
      window.location.href="/odobriCasopis/" + id;
    }else if(name == "Verifikacija recezenta"){
      window.location.href="/odobriRecezenta/" + id;
    }else if(name == "Urednik pregleda podatke o radu"){
      window.location.href = "/pregledRada/" + id;
    }else if(name == "Provera da li je dobro formatiran pdf"){
      window.location.href = "/pregledPdf/" + id;
    }else if(name == "Ispravka PDF-a"){
      window.location.href = "/ispravkaPdf/" +id;
    }else if(name == "Biranje recezenata"){
      window.location.href = "/izaberiRecezenta/" + id;
    }else if(name == "Recenzija rada"){
      window.location.href = "/recenzijaRada/"+id;
    }else if(name == "Odluka urednika o objavljivanju"){
      window.location.href = "/odlukaUrednika/" + id;
    }else if(name == "Izmena teksta"){
      window.location.href = "/izmenaTeksta/" + id;
    }
  }

}
