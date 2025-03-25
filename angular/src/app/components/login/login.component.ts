import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "./service/login.service";
import {UserCredentialsDTO} from "../ainterfaces/user-credentials-dto";
import {JwtService} from "../uniqueServices/jwt.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError: string = '';

  constructor(private fb: FormBuilder, private loginService: LoginService, private jwt: JwtService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  public get email() {
    return this.loginForm.get('email');
  }

  public get password() {
    return this.loginForm.get('password');
  }

  getEmailErrorMessage(): string {
    if (this.email?.hasError('required')) {
      return 'Email jest wymagany';
    }
    if (this.email?.hasError('email')) {
      return 'Podaj poprawny adres email';
    }
    return '';
  }

  getPasswordErrorMessage(): string {
    if (this.password?.hasError('required')) {
      return 'Hasło jest wymagane';
    }
    if (this.password?.hasError('minlength')) {
      return 'Hasło musi mieć co najmniej 6 znaków';
    }
    return '';
  }

  onLogin() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      const userCredentails: UserCredentialsDTO = {
        email: this.email?.value,
        password: this.password?.value
      }

      this.loginService.login(userCredentails).subscribe({
        next: (response) => {
          localStorage.setItem('auth-token', response.token);
          const decodedToken = this.jwt.decodeToken(response.token);
          if (decodedToken) {
            localStorage.setItem('username', decodedToken.username);
            localStorage.setItem('email', decodedToken.email);
          }

          window.location.reload();
          this.router.navigate(['/app']);
        },
        error: (err) => {
          console.error('Błąd logowania', err);
        }
      });
    }
  }
}
