import { Component } from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {AuthService} from "../services/auth.service";
import {User} from "../../models";


@Component({
  selector: 'app-signup-modal',
  templateUrl: './signup-modal.component.html',
  styleUrls: ['./signup-modal.component.css']
})
export class SignupModalComponent {

  constructor(
    public signUpModal: MatDialogRef<SignupModalComponent>,
    private authService: AuthService,
  ) { }

  hidePassword: boolean = true;
  hideRepeatPassword: boolean = true;

  signupForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    repeatPassword: new FormControl('', [Validators.required]),
  }, this.passwordMatchValidator);

  passwordMatchValidator(): ValidatorFn {
    return (fg): ValidationErrors | null => {
      const password = fg.get('password')!.value;
      const repeatPassword = fg.get('repeatPassword')!.value;
      return password && password.value !== repeatPassword.value ? {passwordMismatch: true} : null;
    }
  }

  async signUp() {
    if (this.signupForm.invalid)
      return;

    this.signUpModal.disableClose = true;
    const user: User = {
      username: this.signupForm.get('username')?.value.trim(),
      email: this.signupForm.get('email')?.value.trim(),
      password: this.signupForm.get('password')?.value.trim(),
      token: ''
    }
    // await this.authService.signUp(user);
    const errResponse = await this.authService.signUp(user);
    if (!errResponse)
      this.signUpModal.close();
    else if (errResponse.status === 400) {
      this.signUpModal.disableClose = false;
      this.signupForm.controls['email'].setErrors({emailExists: true});
    }
  }

}
