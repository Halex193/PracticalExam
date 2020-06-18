import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {NodeService} from "./node.service";
import {Node} from "./node.model";

@Component({
  selector: 'app-nodes',
  templateUrl: './nodes.component.html',
  styleUrls: ['./nodes.component.css']
})
export class NodesComponent implements OnInit
{

  errorMessage: string;
  items: Array<Node>;
  @Input() formItem: Node = new Node();
  entityName = "Node"

  constructor(protected service: NodeService, protected router: Router)
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
          this.items = items.sort((n1, n2) => n1.name.localeCompare(n2.name))
          this.items = items;
          console.log("Received items: ", items)
        },
        () => this.errorMessage = "Items not available"
      );
  }

  onSelect(item: Node): void
  {
    console.log("Selected:", item)
    this.copyItem(item)
  }

  delete(item: Node)
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

  getId(node: Node)
  {
    return node.name.toString()
  }

  copyItem(item: Node)
  {
    this.formItem.name = item.name
    this.formItem.totalCapacity = item.totalCapacity
  }

  routeToIdentifiable()
  {
    this.router.navigate(["/identifiable", 5])
  }

}
