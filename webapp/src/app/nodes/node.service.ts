import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Node} from "./node.model"

const URL = 'http://localhost:8080/api/nodes';

@Injectable()
export class NodeService
{
    constructor(private httpClient: HttpClient)
    {
    }

    getItems(): Observable<Node[]>
    {
        return this.httpClient.get<Node[]>(URL);
    }

    getItem(id: string): Observable<Node>
    {
        return this.httpClient.get<Node>(this.identify(URL, id));
    }

    addItem(item: Node): Observable<any>
    {
        return this.httpClient.post<Node>(URL, item);
    }

    updateItem(item: Node): Observable<any>
    {
        const url = this.identify(URL, this.getId(item))
        const headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
        return this.httpClient.put<Node>(url, item, {headers: headers});
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

    getId(node: Node): string
    {
        return node.name.toString()
    }
}
