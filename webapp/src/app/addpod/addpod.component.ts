import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Pod} from "../pods/pod.model";
import {PodService} from "../pods/pod.service";

@Component({
  selector: 'app-pods',
  templateUrl: './addpod.component.html',
  styleUrls: ['./addpod.component.css']
})
export class AddPodComponent implements OnInit
{

  errorMessage: string;
  items: Array<Pod>;
  @Input() formItem: Pod = new Pod();
  entityName = "Pod"

  constructor(protected service: PodService, protected router: Router)
  {
  }

  ngOnInit(): void
  {
    this.getItems();
  }

  getItems()
  {
    this.service.getItems()
      .subscribe(
        items =>
        {
          // this.items = this.filterAndSortItems(items);
          this.items = items;
          console.log("Received items: ", items)
        },
        () => this.errorMessage = "Items not available"
      );
  }

  onSelect(item: Pod): void
  {
    console.log("Selected:", item)
    this.copyItem(item)
  }

  delete(item: Pod)
  {
    console.log("Deleting:", item)
    this.service.deleteItem(this.getId(item))
      .subscribe(_ => this.getItems(), () => this.errorMessage = this.entityName + " does not exist in the database");
  }

  add()
  {
    if (this.formItem.cost < 0)
    {
      this.errorMessage = "Cost cannot be negative"
      return
    }
    if (this.formItem.name.length != 3)
    {
      this.errorMessage = "Name must contain only 3 letters"
      return
    }
    console.log("Adding:", this.formItem)
    this.service.addItem(this.formItem)
      .subscribe(_ => this.router.navigate(["/pods"]), () => this.errorMessage = this.entityName + " already exists in the database");
  }

  getId(pod: Pod)
  {
    return pod.id.toString()
  }

  copyItem(item: Pod)
  {
    this.formItem.id = item.id
    this.formItem.name = item.name
    this.formItem.cost = item.cost
  }

  routeToIdentifiable()
  {
    this.router.navigate(["/identifiable", 5])
  }

}
