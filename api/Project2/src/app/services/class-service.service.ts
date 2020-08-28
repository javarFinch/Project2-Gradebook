import { AdminTeacher } from './../Models/admin/admin-teacher';
import { AdminStudent } from './../Models/admin/admin-student';
import { AdminClass } from './../Models/admin/admin-class';
import { TeacherClass } from './../Models/teacher/teacher-class';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Class } from '../Models/class';
import { User } from '../Models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  public user:User;

  public apiUrl: string = window.location.protocol + "//" + window.location.host + "/api";
  

  constructor(private http: HttpClient) {
    // console.log("host",window.)
   }

   getClassList(id:number): Observable<Class[]> {
     return this.http.get<Class[]>(this.apiUrl+'/student/'+id);
   }

   getTeacherClassList(id:number): Observable<TeacherClass[]> {
    return this.http.get<TeacherClass[]>(this.apiUrl+'/teacher/'+id);
  }

   getUserByName(name:number,password:string):Observable<User>{
     return this.http.get<User>(this.apiUrl+'/user/'+name+'/'+password);
   }

   updateGrades(formData:FormData):Observable<any>{
     return this.http.post(this.apiUrl+'/teacher/update',formData);
   }

   newAssignment(formData:FormData):Observable<any>{
    return this.http.post(this.apiUrl+'/teacher/newAssignment',formData);
  }

   updateClass(id:number): Observable<TeacherClass>{
     return this.http.get<TeacherClass>(this.apiUrl+'/teacher/updateClass/'+id);
   }

   updateUser(formData:FormData): Observable<User>{
    return this.http.put<User>(this.apiUrl+'/user/update/',formData);
  }
  getAdminClass(): Observable<AdminClass[]> {
    return this.http.get<AdminClass[]>(this.apiUrl+'/admin/classes');
  }
  getAdminStudent(): Observable<AdminStudent[]> {
    return this.http.get<AdminStudent[]>(this.apiUrl+'/admin/student');
  }
  getAdminTeacher(): Observable<AdminTeacher[]> {
    return this.http.get<AdminTeacher[]>(this.apiUrl+'/admin/teacher');
  }

  newUser(formData:FormData):Observable<User>{
    return this.http.post<User>(this.apiUrl+'/admin/newUser',formData);
  }
  newClass(formData:FormData):Observable<any>{
    return this.http.post(this.apiUrl+'/admin/newClass',formData);
  }
}

