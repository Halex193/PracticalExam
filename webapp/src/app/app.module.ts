import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { SensorsComponent } from './sensors/sensors.component';
import {SensorService} from "./sensors/sensor.service";

@NgModule({
  declarations: [
    AppComponent,
    SensorsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [SensorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
