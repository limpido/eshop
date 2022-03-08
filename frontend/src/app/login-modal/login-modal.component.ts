import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {SignupModalComponent} from "../signup-modal/signup-modal.component";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent {

  constructor(
    public loginModal: MatDialogRef<SignupModalComponent>,
    private authService: AuthService
  ) { }

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  });
  hidePassword: boolean = true;

  async login() {
    if (this.loginForm.invalid)
      return;

    this.loginModal.disableClose = true;
    const email = this.loginForm.get('email')?.value.trim();
    const password = this.loginForm.get('password')?.value.trim();
    const errResponse = await this.authService.login(email, password);
    if (!errResponse)
      this.loginModal.close();
    else {
      this.loginModal.disableClose = false;
      console.log(errResponse.status);
      switch (errResponse.status) {
        case 400:
          this.loginForm.controls['email'].setErrors({notExists: true})
          break;
        case 401:
          this.loginForm.controls['password'].setErrors({wrong: true});
          break;
      }
    }
  }

}
