import { Component } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AuthService } from './service/AuthService';
import { Router } from '@angular/router';
import { User } from './models/user';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {

  constructor(
    public authService: AuthService) {
    this.retrieveCodeFromRedirectedKeyCloak();
  }
  public async retrieveCodeFromRedirectedKeyCloak() {

    let i = window.location.href.indexOf('code');

    if (i > 0) {
      this.authService.retrieveCodeFromRedirectedKeyCloak(window.location.href.substring(i + 5));
    }
  }

}
