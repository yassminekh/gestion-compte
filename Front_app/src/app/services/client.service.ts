import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../models/client.model';


@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = 'http://localhost:8080/api/clients';

  constructor(private http: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  createClient(client: Client): Observable<Client> {
    return this.http.post<Client>(this.apiUrl, client);
  }

  deleteClient(cin: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${cin}`);
  }
  updateClient(cin: string, client: Client): Observable<Client> {
    const url = `${this.apiUrl}/${cin}`;
    return this.http.put<Client>(url, client);
  }
  searchClients(term: string): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.apiUrl}/search?term=${term}`);
  }
}