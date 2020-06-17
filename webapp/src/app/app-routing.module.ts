import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SensorsComponent} from "./sensors/sensors.component";


const routes: Routes = [
  {path: "all", component: SensorsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule
{
}
