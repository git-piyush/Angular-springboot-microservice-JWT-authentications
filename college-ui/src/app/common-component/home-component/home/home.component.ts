import { Component, EventEmitter, Output } from '@angular/core';
import { Chart } from 'chart.js/auto';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { HttpClient } from '@angular/common/http';
import { VacancyService } from '../../../modules/admin/admin-service/vacancy-service/vacancy.service';

@Component({
  selector: 'app-home',
  standalone: false,
  
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  vacancy : any;

  constructor(private http: HttpClient,
    private vacancyService: VacancyService) { }

  @Output() settingsEvent = new EventEmitter<void>();
  ngAfterViewInit(): void {
    this.initializeCharts();
  }
  initializeCharts() {
    this.createSalesChart();
    this.createPerformanceChart();
    this.getVacancy();
  }

  getVacancy(){
    this.vacancyService.getAllVacancy().subscribe((res)=>{
      this.vacancy = res.content;
    })
  }

  createSalesChart() {
    const ctx = (document.getElementById('salesChart') as HTMLCanvasElement).getContext('2d');
    if (ctx) {
      new Chart(ctx, {
        type: 'line',
        data: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June'],
          datasets: [{
            label: 'Sales',
            data: [65, 59, 80, 81, 56, 55],
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
            fill: false
          }]
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
    }
  }
  createPerformanceChart() {
    const ctx = (document.getElementById('performanceChart') as HTMLCanvasElement).getContext('2d');
    if (ctx) {
      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Completed', 'Pending', 'In Progress', 'On Hold'],
          datasets: [{
            label: 'Performance',
            data: [300, 50, 100, 20],
            backgroundColor: ['#28a745', '#ffc107', '#17a2b8', '#dc3545'],
            hoverOffset: 4
          }]
        },
        options: {
          responsive: true
        }
      });
    }
  }
  emitSettingsEvent() {
    this.settingsEvent.emit();
  }
  
}
