import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AmaincontainerComponent} from "./components/amaincontainer/amaincontainer.component";
import {AuthGuard} from "./auth-guard";

const routes: Routes = [
  {
    path: '', component: AmaincontainerComponent, canActivate: [AuthGuard],
    children: [
      { path: 'app', redirectTo: 'app', pathMatch: 'full' },
      {
        path: 'app',
        loadChildren: () => import('./components/post/post.module').then(m => m.PostModule)
      }
    ]
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./components/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
