import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SensorService} from "./sensor.service";
import {Sensor} from "./sensor.model";

@Component({
  selector: 'app-sensors',
  templateUrl: './sensors.component.html',
  styleUrls: ['./sensors.component.css']
})
export class SensorsComponent implements OnInit
{

  errorMessage: string;
  items: Array<Sensor>;
  @Input() formItem: Sensor = new Sensor();
  entityName = "Sensor"

  constructor(protected service: SensorService, protected router: Router)
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

  onSelect(item: Sensor): void
  {
    console.log("Selected:", item)
    this.copyItem(item)
  }

  getId(sensor: Sensor)
  {
    return sensor.id.toString()
  }

  copyItem(item: Sensor)
  {
    this.formItem.id = item.id
    this.formItem.name = item.name
  }

  routeToIdentifiable()
  {
    this.router.navigate(["/identifiable", 5])
  }

}
