import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Class } from '../Model/Class';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  constructor(private http: HttpClient) {
      console.log('Creating Class Service');
   }

   getClassList(): Observable<Class[]> {
     return this.http.get<Class[]>('http://localhost:3000/classList');
   }

   getClassByName(name: string): Observable<Class[]> {
    return this.http.get<Class[]>(`http://localhost:3000/classList=${name}`);
   }
}
