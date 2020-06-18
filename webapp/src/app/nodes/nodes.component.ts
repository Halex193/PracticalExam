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
  editNodes: Array<Node> = []

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

  update(node: Node)
  {
    console.log("Updating:", node)
    this.service.updateItem(node)
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
    let newNode = new Node()
    newNode.name = node.name
    newNode.totalCapacity = node.totalCapacity
    this.editNodes.push(newNode)
  }

  enableAdd()
  {
    this.adding = true;
  }

  save(formNode: Node)
  {
    for (let node of this.editNodes)
    {
      if (node.name == formNode.name)
      {
        this.update(node)
        this.editNodes = this.editNodes.filter( (n) => n.name != node.name)
        return
      }
    }
  }

  editable(formNode: Node)
  {
    for (let node of this.editNodes)
    {
      if (node.name == formNode.name)
      {
        return true;
      }
    }
    return false;
  }

  onKey(value: any, formNode: Node)
  {
    const totalCapacity = event.target.value;
    for (let node of this.editNodes)
    {
      if (node.name == formNode.name)
      {
        node.totalCapacity = +totalCapacity
      }
    }
  }

  getCapacity(formNode: Node)
  {
    for (let node of this.editNodes)
    {
      if (node.name == formNode.name)
      {
        return node.totalCapacity
      }
    }
  }
}
