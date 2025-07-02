import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { catchError, finalize, throwError } from 'rxjs';
import { ProfileService } from '../service/profile.service';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-who-am-i',
  templateUrl: './who-am-i.component.html',
  styleUrls: ['./who-am-i.component.css'],
  imports: [CommonModule]
})
export class WhoAmIComponent implements OnInit {
  profileData: any;
  isLoading = true;
  error: string = '';
  @Output() componentLoaded = new EventEmitter<boolean>();

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    const email = 'petresamuel@gmail.com';
    this.fetchProfileData(email);
  }

  private fetchProfileData(email: string): void {
    this.profileService.getProfileByEmail(email)
      .pipe(
        catchError(error => {
          this.error = `Error fetching profile data: ${error.message || 'Server error'}`;
          console.error(error);
          return throwError(() => new Error(this.error));
        }),
        finalize(() => {
          this.isLoading = false;
          this.componentLoaded.emit(true);
          console.log('WhoAmIComponent: Data fetching complete, componentLoaded emitted.');
        })
      )
      .subscribe({
        next: data => this.profileData = data,
        error: err => console.error('Subscription error:', err),
      });
  }
}