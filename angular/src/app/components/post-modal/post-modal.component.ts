import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ClosableModalBase} from "../uniqueServices/closable-modal-base";

@Component({
  selector: 'app-post-modal',
  templateUrl: './post-modal.component.html',
  styleUrl: './post-modal.component.scss'
})
export class PostModalComponent extends ClosableModalBase implements OnInit{
  @Input() postId: number | null = null;

  post: any;

  ngOnInit() {
    console.log('id: ', this.postId);
    const fakePosts = [
      { id: 1, user: 'Janek', temat: 'Angular 15' },
      { id: 2, user: 'Kasia', temat: 'RxJS w praktyce' }
    ];
    this.post = fakePosts.find(p => p.id === this.postId);
  }
}
