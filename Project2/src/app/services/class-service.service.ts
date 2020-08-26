import { AdminTeacher } from './../Models/admin/admin-teacher';
import { AdminStudent } from './../Models/admin/admin-student';
import { AdminClass } from './../Models/admin/admin-class';
import { TeacherClass } from './../Models/teacher/teacher-class';
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
     return this.http.get<Class[]>('http://localhost:8080/api/student/'+id);
   }

   getTeacherClassList(id:number): Observable<TeacherClass[]> {
    return this.http.get<TeacherClass[]>('http://localhost:8080/api/teacher/'+id);
  }

   getClassByName(name: string): Observable<Class[]> {
    return this.http.get<Class[]>(`http://localhost:3000/classList=${name}`);
   }

   getUserByName(name:string,password:string):Observable<User>{
     return this.http.get<User>('http://localhost:8080/api/user/'+name+'/'+password);
   }

   updateGrades(formData:FormData):Observable<any>{
     return this.http.post('http://localhost:8080/api/teacher/update',formData);
   }

   newAssignment(formData:FormData):Observable<any>{
    return this.http.post('http://localhost:8080/api/teacher/newAssignment',formData);
  }

   updateClass(id:number): Observable<TeacherClass>{
     return this.http.get<TeacherClass>('http://localhost:8080/api/teacher/updateClass/'+id);
   }
   
   updateUser(formData:FormData): Observable<User>{
    return this.http.put<User>('http://localhost:8080/api/user/update/',formData);
  }
  getAdminClass(): Observable<AdminClass[]> {
    return this.http.get<AdminClass[]>('http://localhost:8080/api/admin/classes');
  }
  getAdminStudent(): Observable<AdminStudent[]> {
    return this.http.get<AdminStudent[]>('http://localhost:8080/api/admin/student');
  }
  getAdminTeacher(): Observable<AdminTeacher[]> {
    return this.http.get<AdminTeacher[]>('http://localhost:8080/api/admin/teacher');
  }
}

