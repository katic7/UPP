import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CasopisService {

  constructor(private http:HttpClient) { }

  kreirajCasopis(str):Observable<any> {
    return this.http.get("http://localhost:8080/welcome/get/"+str);
  }

  getNaucneOblasti():Observable<any>{
    return this.http.get("http://localhost:8080/casopis/getNaucneOblasti");
  }

  getNacinePlacanja():Observable<any>{
    return this.http.get("http://localhost:8080/casopis/getNacinePlacanja");
  }

  napraviCasopis(cas, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/casopis', cas);
  }

  getRecezenti(issn):Observable<any>{
    return this.http.get("http://localhost:8080/casopis/getRecez/"+issn+"/RECEZENT");
  }

  getUrednik(issn):Observable<any>{
    return this.http.get("http://localhost:8080/casopis/getRecez/"+issn+"/UREDNIK");
  }

  izaberiUrednika(user, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/recezentCas', user);
  }

  pregledCas(user, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/sacuvajCas', user);
  }

  getAll():Observable<any>{
    return this.http.get("http://localhost:8080/casopis/getAll");
  }

  odabirCasopisa(cas, taskId):Observable<any>{
    return this.http.post("http://localhost:8080/welcome/post/"+taskId + "/odabirCas",cas);
  }

  startKreirajRad(str,usr):Observable<any> {
    return this.http.get("http://localhost:8080/welcome/get/"+str + "/"+usr);
  }
}
