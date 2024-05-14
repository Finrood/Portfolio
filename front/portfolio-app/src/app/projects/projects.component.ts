import {Component} from '@angular/core';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {catchError, finalize, Observable, throwError} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css',
})
export class ProjectsComponent {
  projectsData: Array<any> = [];
  isLoading = false;
  error: string | null = null;
  cardStates: string[] = [];
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  toggleImageHoverEffect(index: number): void {
    this.cardStates[index] = this.cardStates[index] === 'normal' ? 'hovered' : 'normal';
  }

  ngOnInit(): void {
    this.fetchProjectsData();
  }

  getProjects(): Observable<any> {
    return this.http.get(`${this.baseUrl}/projects`);
  }

  fetchProjectsData(): void {
    this.isLoading = true;
    this.getProjects().pipe(
      catchError((error) => {
        this.error = 'Error while fetching projects data';
        return throwError(() => new Error(error.message || 'Server error'));
      }),
      finalize(() => {
        this.isLoading = false;
      })
    ).subscribe((data: any) => {
      this.projectsData = data;
      this.cardStates = this.projectsData.map(() => 'normal');
    });
  }
}
