import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Sensor} from "./sensor.model";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

const URL = 'http://localhost:8080/api/sensors';

@Injectable()
export class SensorService
{
  constructor(private httpClient: HttpClient)
  {
  }

  getItems(): Observable<Sensor[]>
  {
    return this.httpClient.get<Sensor[]>(URL);
  }

  getItem(id: string): Observable<Sensor>
  {
    return this.httpClient.get<Sensor>(this.identify(URL, id));
  }

  identify(baseURL: any, id: string): string
  {
    return URL + "/" + id
  }

  getId(sensor: Sensor): string
  {
    return sensor.id.toString()
  }
}
