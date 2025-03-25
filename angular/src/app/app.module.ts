import {InjectionToken, NgModule} from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule, provideHttpClient} from "@angular/common/http";
import { NavComponent } from './components/nav/nav.component';
import { PostComponent } from './components/post/post.component';
import { AmaincontainerComponent } from './components/amaincontainer/amaincontainer.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PostModule} from "./components/post/post.module";

export const API_URL = new InjectionToken<string>('API_URL')

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    AmaincontainerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    PostModule,
  ],
  providers: [
    provideHttpClient(),
    {
      provide: API_URL, useValue: 'http://localhost:8800'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
