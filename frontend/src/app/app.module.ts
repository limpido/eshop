import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {ReactiveFormsModule} from "@angular/forms";
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import {FlexLayoutModule} from "@angular/flex-layout";
import { NavbarComponent } from './navbar/navbar.component';
import { SignupModalComponent } from './signup-modal/signup-modal.component';
import {MatDialogModule} from "@angular/material/dialog";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { LoginModalComponent } from './login-modal/login-modal.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { GameStoreComponent } from './game-store/game-store.component';
import { GameCardComponent } from './game-card/game-card.component';
import {MatCardModule} from '@angular/material/card';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import {MatDividerModule} from '@angular/material/divider';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    SignupModalComponent,
    LoginModalComponent,
    GameStoreComponent,
    GameCardComponent,
    ShoppingCartComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    FlexLayoutModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCardModule,
    MatDividerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
