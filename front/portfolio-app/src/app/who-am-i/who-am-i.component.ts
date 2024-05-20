import { Component, OnInit } from '@angular/core';
import { catchError, finalize, Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { animate, style, transition, trigger } from '@angular/animations';
import { environment } from '../../environments/environment';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-who-am-i',
  standalone: true,
  imports: [NgIf, NgForOf],
  templateUrl: './who-am-i.component.html',
  styleUrls: ['./who-am-i.component.css'],
  animations: [
    trigger('scrollAnimation', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms', style({ opacity: 1 })),
      ]),
    ]),
  ],
})
export class WhoAmIComponent implements OnInit {
  profileData: any;
  isLoading = false;
  error: string = '';  // Initialize as an empty string
  private readonly baseUrl: string = `${environment.apiUrl}/profile`;
  skeletonItems = Array(4); // Number of skeleton loader items

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const email = 'petresamuel@gmail.com';
    this.fetchProfileData(email);
  }

  scrollToProjects(): void {
    const section = document.getElementById('projects');
    if (section) {
      const navbarHeight = (document.querySelector('nav') as HTMLElement).clientHeight;
      const offset = section.getBoundingClientRect().top + window.scrollY - navbarHeight;
      window.scrollTo({ top: offset, behavior: 'smooth' });
    }
  }

  private getProfileByEmail(email: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${email}`);
  }

  private fetchProfileData(email: string): void {
    this.isLoading = true;
    this.getProfileByEmail(email).pipe(
      catchError(error => {
        this.error = 'Error while fetching profile data: ' + (error.message || 'Server error');
        return throwError(() => new Error(this.error || 'Unknown error'));
      }),
      finalize(() => {
        this.isLoading = false;
      })
    ).subscribe({
      next: data => this.profileData = data,
      error: err => console.error(err)
    });
  }
}
