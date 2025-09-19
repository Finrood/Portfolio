import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, finalize, Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

interface Project {
  title: { translations: { en: string } };
  description: { translations: { en: string } };
  githubUrl: string;
}



@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css'],
  imports: []
})
export class ProjectsComponent implements OnInit {
  projectsData: Project[] = [];
  isLoading = true;
  error: string | null = null;
  @Output() componentLoaded = new EventEmitter<boolean>();

  private readonly baseUrl: string = `${environment.apiUrl}/projects`;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchProjectsData();
  }

  fetchProjectsData(): void {
    this.http.get<Project[]>(this.baseUrl).pipe(
      catchError(error => {
        this.error = 'Error while fetching projects data: ' + (error.message || 'Server error');
        return throwError(() => new Error(this.error || 'Unknown error'));
      }),
      finalize(() => {
          this.isLoading = false;
          this.componentLoaded.emit(true);
          console.log('ProjectsComponent: Data fetching complete, componentLoaded emitted.');
        })
    ).subscribe({
      next: data => {
        this.projectsData = data;
      },
      error: err => console.error(err)
    });
  }
}