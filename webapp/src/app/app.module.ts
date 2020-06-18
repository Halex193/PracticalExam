import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { NodesComponent } from './nodes/nodes.component';
import {NodeService} from "./nodes/node.service";
import { PodsComponent } from './pods/pods.component';
import {PodService} from "./pods/pod.service";
import {AddPodComponent} from "./addpod/addpod.component";

@NgModule({
  declarations: [
    AppComponent,
    NodesComponent,
    PodsComponent,
    AddPodComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [NodeService, PodService],
  bootstrap: [AppComponent]
})
export class AppModule { }
