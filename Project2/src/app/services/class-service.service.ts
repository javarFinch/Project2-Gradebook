import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Class } from '../Models/Class';
import { User } from '../Models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  constructor(private http: HttpClient) {
      console.log('Creating Class Service');
   }

   getClassList(id:number): Observable<Class[]> {
     return this.http.get<Class[]>('http://localhost:8080/app/api/student/'+id);
   }

   getClassByName(name: string): Observable<Class[]> {
    return this.http.get<Class[]>(`http://localhost:3000/classList=${name}`);
   }

   getUserByName(name:string,password:string):Observable<User>{
     return this.http.get<User>('http://localhost:8080/app/api/user/'+name+'/'+password);
   }
}

