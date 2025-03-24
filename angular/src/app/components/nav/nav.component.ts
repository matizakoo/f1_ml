import { Component } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss'
})
export class NavComponent {
  public email: string;
  constructor() {
    this.email = localStorage.getItem('email') ?? '';
  }
}
