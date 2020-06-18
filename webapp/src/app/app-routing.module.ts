import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NodesComponent} from "./nodes/nodes.component";
import {PodsComponent} from "./pods/pods.component";


const routes: Routes = [
  {path: "nodes", component: NodesComponent},
  {path: "pods", component: PodsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule
{
}
