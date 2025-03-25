import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PostRoutingModule} from "./post-routing.module";
import {FormsModule} from "@angular/forms";
import {PostComponent} from "./post.component";
import { PostModalComponent } from './post-modal/post-modal.component';



@NgModule({
  declarations: [
    PostComponent,
    PostModalComponent
  ],
  imports: [
    CommonModule,
    PostRoutingModule,
    FormsModule,
  ],
  exports: [
  ]
})
export class PostModule { }
