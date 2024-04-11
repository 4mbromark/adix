import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { RouteLink } from "../app-routing.namespace";
import { AuthenticationService } from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationUnloggedGuard implements CanActivate {

  constructor(
    private readonly authentication: AuthenticationService,
    private readonly router: Router
  ) { }

  canActivate(): Promise<boolean> {
    return new Promise((resolve) => {
      this.authentication.verify().then(() => {
        this.router.navigateByUrl(RouteLink.HOME);
        resolve(false);
      }).catch(() => {
        resolve(true);
      });
    });
  }
}
