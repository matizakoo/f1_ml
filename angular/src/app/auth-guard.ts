import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree => {
    const isLoggedIn = this.authService.isLoggedIn();

    if (isLoggedIn && state.url.includes('/auth/login')) {
      // Jeśli użytkownik jest zalogowany i próbuje dostać się do /auth/login, przekieruj na stronę główną
      return this.router.parseUrl('/app');
    } else if (!isLoggedIn && !state.url.includes('/login')) {
      // Jeśli użytkownik nie jest zalogowany i próbuje dostać się do innej strony niż /auth/login
      return this.router.parseUrl('/login');
    }

    return true;  // Pozwól na przejście do strony, jeśli powyższe warunki nie są spełnione
  }
}
