import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }

  startProcess(str): Observable<any>{
    return this.http.get("http://localhost:8080/welcome/get/"+str);
  }

  registerUser(user, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/registracija', user);
  }

  getUserTask(pid):Observable<any>{
    return this.http.get("http://localhost:8080/welcome/getUserTask/"+pid);
  }

  getUserTask2(pid):Observable<any>{
    return this.http.get("http://localhost:8080/welcome/getUserTask2/"+pid);
  }

  aktivacijaKor(user, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/aktivacijaKor', user);
  }

  getMyTasks(username): Observable<any>{
    return this.http.get("http://localhost:8080/welcome/getMyTasks/"+username);
  }

  getTaskById(id):Observable<any>{
    return this.http.get("http://localhost:8080/welcome/getTask/"+id);
  }

  odobriRecezenta(user, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/odobriRec', user);
  }

  

}

