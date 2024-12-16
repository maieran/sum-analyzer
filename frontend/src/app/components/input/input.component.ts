import { Component } from '@angular/core';
import { BackendService, ResultDTO } from '../../services/backend.service';
import { AnalysisService } from '../../services/analysis.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Importiere das CommonModule
import { timestamp } from 'rxjs';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [FormsModule, CommonModule], // CommonModule hinzugefügt
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css'],
})
export class InputComponent {
  selectedTechnology: string = 'Frontend';
  inputValue: number = 0;
  previousSum: number | null = null;
  currentSum: number | null = null;
  resultTree: { [key: number]: number } | null = null;
  differenceTree: {
[x: string]: any; [key: number]: number 
} | null = null;
Object: any;

  constructor(private backendService: BackendService,
    private frontendService: AnalysisService
  ) {}
  


  startAnalysis(): void {
    console.log('Start Analysis triggered, selectedTechnology:', this.selectedTechnology);

    if (this.selectedTechnology === 'Frontend') {
      this.runFrontendAnalysis();
    } else {
      this.runBackendAnalysis();
    }
  }

  private runBackendAnalysis(): void {
    const input = { value: this.inputValue, timestamp: new Date().toISOString() };
  
    this.backendService.sendInput(input).subscribe((result: ResultDTO) => {
      this.previousSum = this.currentSum;
      this.currentSum = this.inputValue;
  
      this.resultTree = result.resultTree;
      this.differenceTree = result.differenceTree;
    });
  }

  private runFrontendAnalysis(): void {
    const input = { value: this.inputValue, timestamp: new Date().toISOString() };
    console.log("Input - Value: " + input.value);
    console.log("Input - Timestamp: " + input.timestamp);

    this.frontendService.analyzeValue(input).subscribe((result: ResultDTO) => {
      this.previousSum = this.currentSum;
      this.currentSum = this.inputValue;


      console.log('Result Tree:', result.resultTree);
      console.log('Difference Tree:', result.differenceTree);
      this.resultTree = result.resultTree;
      this.differenceTree = result.differenceTree;
    });
  }

  getTreeEntries(tree: { [key: string]: number }): { key: number; value: number }[] {
    if (!tree || Object.keys(tree).length === 0) {
      return [];
    }
  
    return Object.keys(tree)
      .map((key) => {
        const numKey = Number(key);//Konvertieren den Schlüssel in eine Zahl
        return {
          key: Math.round(numKey * 100) / 100,//Auf 2 Nachkommastellen begrenzen
          value: tree[key],
        };
      })
      .sort((a, b) => b.key - a.key);
  }
  
}
