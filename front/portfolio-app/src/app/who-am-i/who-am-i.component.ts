import { Component } from '@angular/core';
import {catchError, finalize, Observable, throwError} from "rxjs";
import { HttpClientModule, HttpClient } from '@angular/common/http';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-who-am-i',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './who-am-i.component.html',
  styleUrl: './who-am-i.component.css'
})
export class WhoAmIComponent {
  private baseUrl = 'http://localhost:8091/profile';

  profileData: any;
  isLoading = false;
  error: string | null = null;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    const email = 'petresamuel@gmail.com';
    this.fetchProfileData(email);
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
