import { Component, Input, OnInit } from '@angular/core';
import { CommonService } from '../../service/CommonService';
import { User } from '../../models/user';
import { UserService } from '../../service/UserService';
import { AuthService } from '../../service/AuthService';

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(
    private commonService: CommonService,
    private userService: UserService,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login();
  }

  logout(): void {

    this.authService.logout();
    this.userService.setUserCookie(new User());
  }

  isLogin(): boolean {
    return this.userService.isLogin();
  }

  getUserCookies(): User {
    return this.userService.getUserCookie();
  }

  isDesktop(): boolean {
    return this.commonService.isDesktop();
  }


}
