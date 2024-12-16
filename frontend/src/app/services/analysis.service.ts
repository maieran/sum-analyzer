import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { BackendService, InputDataDTO } from './backend.service';

export interface InputDTO {
  value: number;
  timestamp: string;
}

export interface ResultDTO {
  resultTree: { [key: number]: number };
  differenceTree: { [key: number]: number };
}

@Injectable({
  providedIn: 'root',
})
export class AnalysisService {
  private coinsAndBankNotes: number[] = [
    200, 100, 50, 20, 10, 5, 2, 1, 0.50, 0.20, 0.10, 0.05, 0.02, 0.01];

  constructor(private backendService: BackendService) {}

  analyzeValue(currentInput: InputDTO): Observable<ResultDTO> {
    
    
    return new Observable<ResultDTO>((observer) => {
      this.backendService.getLatestInputData().subscribe({
        next: (latestInputData) => {
          console.log('Latest Input Data:', latestInputData);
  
          const previousInput = latestInputData && latestInputData.value != null 
            ? latestInputData 
            : null;
  
          console.log('Previous Input:', previousInput);
  
          const resultDTO = this.analyzeSum(previousInput, currentInput);
  
          this.backendService.saveInput(currentInput);

          observer.next(resultDTO);
          observer.complete();
        },
        error: (err) => {
          console.error('Fehler beim Abrufen des letzten Inputs:', err);
        },
      });
    });
  }
  

  analyzeSum(previousInput: InputDataDTO | null, currentInput: InputDTO): ResultDTO {
    const resultDTO: ResultDTO = { resultTree: {}, differenceTree: {} };
  
    const currentSum = currentInput.value;
  
    if (previousInput && previousInput.value != null) {
      console.log('Previous Input Value:', previousInput.value);
  
      //Falls previousInput existiert, führe Differenzberechnung durch
      const previousSum: number = previousInput.value;
      const previousTree = this.doSumFitting(previousSum);
      const currentTree = this.doSumFitting(currentSum);
      const differenceTree = this.doDifferenceSumFitting(previousTree, currentTree);
  
      resultDTO.resultTree = currentTree;
      resultDTO.differenceTree = differenceTree;
    } else {
      console.warn('Previous Input is null or invalid. Calculating only current sum.');
  
      //Nur aktuelle Ergebnis-Analyse durchführen
      const currentTree = this.doSumFitting(currentSum);
      resultDTO.resultTree = currentTree;
      resultDTO.differenceTree = {};
    }
    return resultDTO;
  }


  private doDifferenceSumFitting(
    previousSumFitting: { [key: number]: number },
    currentSumFitting: { [key: number]: number }
  ): { [key: number]: number } {
    const differenceFittings: { [key: number]: number } = {};

    for (const coinOrBankNote of this.coinsAndBankNotes) {
      const previousCount = previousSumFitting[coinOrBankNote] || 0;
      const currentCount = currentSumFitting[coinOrBankNote] || 0;
      differenceFittings[coinOrBankNote] = currentCount - previousCount;
    }
  
    return differenceFittings;
  }
  

  doSumFitting(inputSum: number): { [key: number]: number } {
    const sumFitting: {[key:number]: number} = {};
    let remainingCents = Math.round(inputSum * 100);
    
    for (const currentFit of this.coinsAndBankNotes) {
      const currentFitInCents = Math.round(currentFit * 100);
      if (remainingCents >= currentFitInCents) {
        const count = Math.floor(remainingCents/ currentFitInCents);
        sumFitting[currentFit] = count;
        remainingCents %= currentFitInCents
      }
    }

    return sumFitting;
  }
}