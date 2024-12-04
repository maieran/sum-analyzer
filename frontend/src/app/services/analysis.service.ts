import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AnalysisService {
  private previousValue: number | null = null;

  analyzeValue(currentValue: number): number {
    if (this.previousValue === null) {
      this.previousValue = currentValue;
      //return `Current value: ${currentValue} (No previous value available)`;
      return currentValue;
    }

    const difference = currentValue - this.previousValue;
    this.previousValue = currentValue;
    /* return `Current value: ${currentValue}, Previous value: ${this.previousValue}, Difference: ${difference}`; */
    return difference;
  }
}
