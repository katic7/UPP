import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/service/auth.service';
import { TokenStorageService } from 'src/app/auth/token-storage/token-storage.service';
import { Router } from '@angular/router';
import { JWTAuth } from 'src/app/auth/response/jwt-auth';
import { LogInRequest } from 'src/app/model/LogInRequest';

@Component({
  selector: 'app-logovanje',
  templateUrl: './logovanje.component.html',
  styleUrls: ['./logovanje.component.css']
})
export class LogovanjeComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  validEmail = false;
  errorMessage = '';
  private loginInfo: LogInRequest;
  private jwtauth: JWTAuth;
  private element;

  constructor(private tokenStorage: TokenStorageService, private router: Router,
    private authService: AuthService) { }

  register() {
    this.router.navigate(['register']);
  }

  checkEmail() {
    this.authService.checkEmail(this.form.username).subscribe(data => {
      this.validEmail = true;
      this.isLoginFailed = false;


    }, error => {
      this.errorMessage = error.error.errorMessage;
      this.isLoginFailed = true;
    })
  }

  onSubmit() {
    console.log(this.form);
    this.loginInfo = new LogInRequest(
      this.form.username, // email zapravo 
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.table(data);
        this.jwtauth = data;
        this.tokenStorage.saveToken(data.accessToken);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(['mojiTaskovi']);        
      },
      error => {
        this.errorMessage = "Wrong password, please try again."
        this.isLoginFailed = true;
      }
    );
  }

  ngOnInit() {
    this.authService.getLogged().subscribe(data=>{
      this.router.navigate(['mojiTaskovi']);
    },err=>{
      console.log("nije logovan");
    })

  }

}
