import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NodesComponent} from "./nodes/nodes.component";
import {PodsComponent} from "./pods/pods.component";
import {AddPodComponent} from "./addpod/addpod.component";


const routes: Routes = [
  {path: "nodes", component: NodesComponent},
  {path: "pods", component: PodsComponent},
  {path: "pods/new", component: AddPodComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule
{
}
