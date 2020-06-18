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
  adding = false;
  editNode: Node = new Node()

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
    this.adding=false;
    console.log("Adding:", this.formItem)
    this.service.addItem(this.formItem)
      .subscribe(_ => this.getItems(), () => this.errorMessage = this.entityName + " data is invalid!");
  }

  update()
  {
    console.log("Updating:", this.editNode)
    this.service.updateItem(this.editNode)
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

  edit(node: Node)
  {
    this.editNode.name = node.name
  }

  enableAdd()
  {
    this.adding = true;
  }

  save()
  {
    this.update()
    this.editNode = new Node()
  }
}
