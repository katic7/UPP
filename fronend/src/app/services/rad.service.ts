import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RadService {

  constructor(private http:HttpClient) { }

  uploadDocument( fileToUpload: File) {
    const formData: FormData = new FormData();  
    formData.append("File", fileToUpload);
    return this.http.post('http://localhost:8080/rad/uploadPDF/', formData,{ responseType: 'text'});
  }

  napraviRad(rad, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/cuvanjeRada', rad);
  }

  setAutora(id,idUser):Observable<any>{
    return this.http.get("http://localhost:8080/rad/setAutora/".concat(id) + "/".concat(idUser));
  }

  pregledRada(rad, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/pregledRada', rad);
  }

  pregledPdf(rad, taskId):Observable<any>{
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/pregledPdf', rad);
  }

  ispraviPdf(rad,taskId):Observable<any>{
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/noviPdf', rad);
  }

  setRecezente(rad, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/cuvanjeRecez', rad);
  }

  pregledajRad(rad, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/pregledajRad', rad);
  }

  urednikPreglda(rad, taskId):Observable<any> {
    return this.http.post("http://localhost:8080/welcome/post/"+taskId +'/urednikPregleda', rad);
  }
}
