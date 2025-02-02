import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Compte } from '../models/compte.model';

@Injectable({
  providedIn: 'root'
})
export class CompteService {
  private apiUrl = 'http://localhost:8080/api/comptes';

  constructor(private http: HttpClient) { }

  getAllComptes(): Observable<Compte[]> {
    return this.http.get<Compte[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }


  getCompteByRib(rib: number): Observable<Compte> {
    return this.http.get<Compte>(`${this.apiUrl}/${rib}`).pipe(
      catchError(this.handleError)
    );
  }


  addCompte(compte: Compte): Observable<Compte> {
    return this.http.post<Compte>(this.apiUrl, compte).pipe(
      catchError(this.handleError)
    );
  }


  deleteCompte(rib: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${rib}`).pipe(
      catchError(this.handleError)
    );
  }


  updateCompte(rib: number, compte: Compte): Observable<Compte> {
    return this.http.put<Compte>(`${this.apiUrl}/${rib}`, compte).pipe(
      catchError(this.handleError)
    );
  }


  private handleError(error: HttpErrorResponse) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('Something went wrong; please try again later.'));
  }
}