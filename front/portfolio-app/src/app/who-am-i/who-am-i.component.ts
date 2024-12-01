import { Component, OnInit } from '@angular/core';
import { catchError, finalize, throwError } from 'rxjs';
import { animate, style, transition, trigger } from '@angular/animations';
import { ProfileService } from '../service/profile.service';
import {NgForOf, NgIf} from "@angular/common";

@Component({
    selector: 'app-who-am-i',
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
    imports: [
        NgIf,
        NgForOf
    ]
})
export class WhoAmIComponent implements OnInit {
  profileData: any;
  isLoading = false;
  error: string = '';
  skeletonItems = Array(4);

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    const email = 'petresamuel@gmail.com';
    this.fetchProfileData(email);
  }

  scrollToProjects(): void {
    const section = document.getElementById('projects');
    if (section) {
      const navbarHeight = document.querySelector('nav')?.clientHeight ?? 0;
      const offset = section.getBoundingClientRect().top + window.scrollY - navbarHeight;
      window.scrollTo({ top: offset, behavior: 'smooth' });
    }
  }

  private fetchProfileData(email: string): void {
    this.isLoading = true;
    this.profileService.getProfileByEmail(email)
      .pipe(
        catchError(error => {
          this.error = `Error fetching profile data: ${error.message || 'Server error'}`;
          console.error(error);
          return throwError(() => new Error(this.error));
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe({
        next: data => this.profileData = data,
        error: err => console.error('Subscription error:', err),
      });
  }
}
