import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface InputDTO {
  value: number;
  timestamp: string;
}

export interface ResultDTO {
  resultTree : { [key: number]: number };
  differenceTree: { [key: number]: number };
}

export interface InputDataDTO {
  value: number;
}

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  [x: string]: any;
  private apiUrl = 'http://localhost:8080/dedalus-api/sumanalyzer';

  constructor(private http: HttpClient) {}

  sendInput(input: InputDTO): Observable<ResultDTO> {
    return this.http.post<ResultDTO>(`${this.apiUrl}/analyseSum`, input);
  }

  getLatestInputData(): Observable<InputDataDTO> {
    return this.http.get<InputDataDTO>(`${this.apiUrl}/latestInputData`);
  }
}
