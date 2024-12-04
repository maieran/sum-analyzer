import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface InputDTO {
  value: number;
  timestamp: string;
}

export interface ResultDTO {
  message: string;
}

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  private apiUrl = 'http://localhost:8080/dedalus-api/sumanalyzer';

  constructor(private http: HttpClient) {}

  sendInput(input: InputDTO): Observable<ResultDTO> {
    return this.http.post<ResultDTO>(`${this.apiUrl}/analyseSum`, input);
  }

  getLastResult(): Observable<ResultDTO> {
    return this.http.get<ResultDTO>(`${this.apiUrl}/lastResult`);
  }
}
