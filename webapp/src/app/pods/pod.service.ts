import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Pod} from "./pod.model";

const URL = 'http://localhost:8080/api/pods';

@Injectable()
export class PodService
{
    constructor(private httpClient: HttpClient)
    {
    }

    getItems(): Observable<Pod[]>
    {
        return this.httpClient.get<Pod[]>(URL);
    }

    getItem(id: string): Observable<Pod>
    {
        return this.httpClient.get<Pod>(this.identify(URL, id));
    }

    addItem(item: Pod): Observable<any>
    {
        return this.httpClient.post<Pod>(URL, item);
    }

    updateItem(item: Pod): Observable<any>
    {
        const url = this.identify(URL, this.getId(item))
        const headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
        return this.httpClient.put<Pod>(url, item, {headers: headers});
    }

    deleteItem(id: string): Observable<any>
    {
        const url = this.identify(URL, id)
        return this.httpClient.delete(url);
    }

    identify(baseURL: any, id: string): string
    {
        return URL + "/" + id
    }

    getId(pod: Pod): string
    {
        return pod.name.toString()
    }
}
