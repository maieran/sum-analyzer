import { Component } from '@angular/core';
import { BackendService, ResultDTO } from '../../services/backend.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Importiere das CommonModule

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
  differenceTree: { [key: number]: number } | null = null;

  constructor(private backendService: BackendService) {}

  startAnalysis(): void {
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
  
      // Setze die Bäume für die Anzeige
      this.resultTree = result.resultTree;
      this.differenceTree = result.differenceTree;
    });
  }

  private runFrontendAnalysis(): void {
    this.previousSum = this.currentSum;
    this.currentSum = this.inputValue;
  }


  getTreeEntries(tree: { [key: string]: number }): { key: number; value: number }[] {
    if (!tree) {
      return [];
    }
    return Object.keys(tree)
      .map((key) => {
        const numKey = parseFloat(key);
        return {
          key: parseFloat(numKey.toFixed(2)), // Auf 2 Nachkommastellen begrenzen
          value: tree[key],
        };
      })
      .sort((a, b) => b.key - a.key);
  }
}
