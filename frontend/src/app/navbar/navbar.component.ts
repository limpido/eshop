import {Component, Input, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {SignupModalComponent} from "../signup-modal/signup-modal.component";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {LoginModalComponent} from "../login-modal/login-modal.component";
import {User} from "../../models";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    public dialog: MatDialog,
    private router: Router,
    private authService: AuthService
  ) { }

  @Input() user: User | null;
  @Input() showShoppingCartBtn: boolean;

  ngOnInit(): void {
  }

  signUp() {
    const signupModal = this.dialog.open(SignupModalComponent, {
      width: '400px'
    });
    signupModal.afterClosed().subscribe(async () => {
      await this.router.navigate(['']);
    });
  }

  logIn() {
    const loginModal = this.dialog.open(LoginModalComponent, {
      width: '400px'
    });
    loginModal.afterClosed().subscribe(async () => {
      await this.router.navigate(['']);
      // location.reload();
    });
  }

  logOut() {
    this.authService.logOut();
  }

  async onClickShoppingCart(): Promise<void> {
    await this.router.navigate(['shopping-cart']);
  }

  async onClickHome(): Promise<void> {
    await this.router.navigate(['']);
  }

}
