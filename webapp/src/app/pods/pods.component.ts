import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PodService} from "./pod.service";
import {Pod} from "./pod.model";

@Component({
  selector: 'app-pods',
  templateUrl: './pods.component.html',
  styleUrls: ['./pods.component.css']
})
export class PodsComponent implements OnInit
{

  errorMessage: string;
  items: Array<Pod>;
  @Input() formItem: Pod = new Pod();
  entityName = "Pod"
  cost: number = -1

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
    console.log("Adding:", this.formItem)
    this.service.addItem(this.formItem)
      .subscribe(_ => this.getItems(), () => this.errorMessage = this.entityName + " already exists in the database");
  }

  update()
  {
    console.log("Updating:", this.formItem)
    this.service.updateItem(this.formItem)
      .subscribe(_ => this.getItems(), () => this.errorMessage = this.entityName + " does not exist in the database");
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

  addNew()
  {
    this.router.navigate(["/pods/new"])
  }

  filter()
  {
    if (this.cost != -1)
    this.items = this.items.filter(p => p.cost < this.cost)
  }
}
