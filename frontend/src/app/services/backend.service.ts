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

export interface InputData {
  value: number;
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

  getLastResult(): Observable<InputData> {
    return this.http.get<InputData>(`${this.apiUrl}/lastResult`);
  }
}
