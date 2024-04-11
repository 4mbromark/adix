import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { RouteLink } from "../app-routing.namespace";
import { AuthenticationService } from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationLoggedGuard implements CanActivate {

  constructor(
    private readonly authentication: AuthenticationService,
    private readonly router: Router
  ) { }

  canActivate(): Promise<boolean> {
    return new Promise((resolve) => {
      this.authentication.verify().then(() => {
        resolve(true);
      }).catch(() => {
        this.router.navigateByUrl(RouteLink.LOGIN);
        resolve(false);
      });
    });
  }
}
