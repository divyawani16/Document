import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
 { path: 'login', component: LoginComponent, pathMatch: 'full', data: { layout: 'login-layout' } },

  { path: 'home', component: HomeComponent, data: { layout: 'default' } },
  { path: 'dashboard', component: DashboardComponent, data: { layout: 'default' } },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
