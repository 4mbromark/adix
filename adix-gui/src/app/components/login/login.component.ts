import { AuthenticationService } from './../../authentication/authentication.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  protected code: FormControl<string> = new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]);
  protected mail: FormControl<string> = new FormControl('', [Validators.required, Validators.email]);
  protected pswd: FormControl<string> = new FormControl('', [Validators.required]);

  constructor(
    private readonly authentication: AuthenticationService
  ) { }

  ngOnInit(): void {
  }

  protected login(): void {
    if (!this.code.valid || !this.mail.valid || !this.pswd.valid) {
      return;
    }

    this.authentication.login(this.code.value, this.mail.value, this.pswd.value);
  }
}
