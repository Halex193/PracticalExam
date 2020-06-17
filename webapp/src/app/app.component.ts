import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent
{
  title = 'Software';

  constructor(private cookieService: CookieService, private router: Router, private httpClient: HttpClient)
  {
  }

}
