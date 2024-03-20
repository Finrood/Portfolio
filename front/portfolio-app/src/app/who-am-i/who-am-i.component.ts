import {Component} from '@angular/core';
import {catchError, finalize, Observable, throwError} from "rxjs";
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {CommonModule, ViewportScroller} from "@angular/common";
import {animate, style, transition, trigger} from "@angular/animations";



@Component({
  selector: 'app-who-am-i',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './who-am-i.component.html',
  styleUrl: './who-am-i.component.css',
  animations: [
    trigger('scrollAnimation', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms', style({ opacity: 1 })),
      ]),
    ]),
  ],
})
export class WhoAmIComponent {
  profileData: any;
  isLoading = false;
  error: string | null = null;
  private baseUrl = 'http://localhost:8091/profile';

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    const email = 'petresamuel@gmail.com';
    this.fetchProfileData(email);
  }

  scrollToProjects() {
    const section = document.getElementById("projects")
    if (section) {
      // @ts-ignore
      const navbarHeight = document.querySelector('nav').clientHeight;
      const offset = section.getBoundingClientRect().top + window.scrollY - navbarHeight;
      window.scrollTo({ top: offset, behavior: 'smooth' });
    }
  }

  getProfileByEmail(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${email}`);
  }

  fetchProfileData(email: string): void {
    this.isLoading = true;
    this.getProfileByEmail(email).pipe(
      catchError((error) => {
        this.error = 'Error while fetching profile data';
        return throwError(() => new Error(error.message || 'Server error'));
      }),
      finalize(() => {
        this.isLoading = false;
      })
    ).subscribe((data: any) => {
      this.profileData = data;
    });
  }
}
