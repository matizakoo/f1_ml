import {Component, OnInit} from '@angular/core';
import {PostService} from "./service/post.service";
import {PostDTO} from "../models/post-dto";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent implements OnInit{
  topic = '';
  posts: PostDTO[] = [];

  constructor(private postService: PostService) {
  }

  ngOnInit(): void {
    this.getPosts();
  }

  showPopup = false;
  selectedPostId: number | null = null;

  openPopup(postId: number) {
    this.selectedPostId = postId;
    this.showPopup = true;
  }

  closePopup() {
    this.showPopup = false;
    this.selectedPostId = null;
  }

  addPost() {
    const email = localStorage.getItem('email');

    if (!email || !this.topic) {
      alert('Email i temat są wymagane');
      return;
    }

    this.postService.addPost(this.topic, email).subscribe({
      next: () => {
        alert('Post dodany!');
        this.topic = '';
      },
      error: err => console.error('Błąd przy dodawaniu posta', err)
    });
  }

  getPosts() {
    this.postService.getAllPosts().subscribe({
      next: (data) => this.posts = data
    });

  }
}
