import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, finalize, Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import {NgForOf, NgIf} from "@angular/common";

interface Project {
  title: { translations: { en: string } };
  description: { translations: { en: string } };
  githubUrl: string;
}

@Component({
  selector: 'app-projects',
  standalone: true,
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css'],
  imports: [
    NgIf,
    NgForOf
  ]
})
export class ProjectsComponent implements OnInit {
  projectsData: Project[] = [];
  isLoading = false;
  error: string | null = null;
  cardStates: string[] = [];

  private readonly baseUrl: string = `${environment.apiUrl}/projects`;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchProjectsData();
  }

  toggleImageHoverEffect(index: number): void {
    this.cardStates[index] = this.cardStates[index] === 'normal' ? 'hovered' : 'normal';
  }

  fetchProjectsData(): void {
    this.isLoading = true;
    this.http.get<Project[]>(this.baseUrl).pipe(
      catchError(error => {
        this.error = 'Error while fetching projects data: ' + (error.message || 'Server error');
        return throwError(() => new Error(this.error || 'Unknown error'));
      }),
      finalize(() => {
        this.isLoading = false;
      })
    ).subscribe({
      next: data => {
        this.projectsData = data;
        this.cardStates = this.projectsData.map(() => 'normal');
      },
      error: err => console.error(err)
    });
  }
}
