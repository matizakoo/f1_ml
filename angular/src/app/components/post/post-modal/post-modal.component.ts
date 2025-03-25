import {Component, Input, OnInit} from '@angular/core';
import {ClosableModalBase} from "../../uniqueServices/closable-modal-base";
import {PostDTO} from "../../models/post-dto";
import {PostService} from "../service/post.service";

@Component({
  selector: 'app-post-modal',
  templateUrl: './post-modal.component.html',
  styleUrl: './post-modal.component.scss'
})
export class PostModalComponent extends ClosableModalBase implements OnInit{
  @Input() postId!: number;
  comment = '';
  // post: any;
  post: PostDTO | undefined ;

  constructor(private postService: PostService) {
    super();
  }

  ngOnInit() {
    this.getSinglePost();
  }

  getSinglePost() {
    this.postService.getSinglePost(this.postId).subscribe({
      next: (data) => {
        this.post = data;
        console.log('Post załadowany:', this.post); // 👈 TUTAJ!
      },
      error: (err) => console.error('Błąd ładowania posta', err)
    });
  }

  addNewComment() {
    this.postService.addNewComment(this.comment, this.postId).subscribe();
  }
}
