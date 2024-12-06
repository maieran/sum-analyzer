import { Component } from '@angular/core';
import { BackendService } from '../../services/backend.service';
import { AnalysisService } from '../../services/analysis.service';
import { FormsModule } from '@angular/forms';
import { InputData } from '../../services/backend.service';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css'],
})

export class InputComponent {
  selectedTechnology: string = 'Frontend'; // Default: Frontend
  inputValue: number = 0;
  previousSum: number | null = null;
  currentSum: number | null = null;

  constructor(
    private backendService: BackendService,
    private analysisService: AnalysisService,
    private inputData: InputData
  ) {}


  startAnalysis(): void {
    if (this.selectedTechnology === 'Frontend') {
      this.runFrontendAnalysis();
    } else {
      this.runBackendAnalysis();
    }
  }

  // Für Spring Boot im Backend
  private runBackendAnalysis(): void {
    const input = { value: this.inputValue, timestamp: new Date().toISOString() };

    this.backendService.sendInput(input).subscribe((result) => {
/*   this.previousSum = this.currentSum;
     this.currentSum = parseFloat(result); // Assuming the backend returns the result in `message`. */
     /* this.inputData = this.backendService.getLastResult();  */
    });
  }

    // Für Angular im Frontend
    private runFrontendAnalysis(): void {
      this.previousSum = this.currentSum;
      this.currentSum = this.analysisService.analyzeValue(this.inputValue);
    }
}
