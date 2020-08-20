import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentComponent } from './student/student.component'


const routes: Routes = [
  {
    path:'student',
    component: StudentComponent,
  },
  {
    path:'login',
    component: LoginComponent,
  },
  {
    path: '**',
    redirectTo: '/login'
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
