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

    if (isLoggedIn && state.url.includes('/login')) {
      console.log('zalogowany');
      return this.router.parseUrl('/app');
    } else if (!isLoggedIn && !state.url.includes('/login')) {
      console.log('nieezalogowany zalogowany');
      return this.router.parseUrl('/login');
    }
    console.log('else')
    return true;
  }
}
