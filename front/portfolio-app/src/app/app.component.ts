import {Component, ElementRef, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet, Event} from '@angular/router';
import {WhoAmIComponent} from "./who-am-i/who-am-i.component";
import {ProjectsComponent} from "./projects/projects.component";
import {ContactComponent} from "./contact/contact.component";

import { IStaticMethods } from 'preline/preline';
import {NgOptimizedImage} from "@angular/common";

declare global {
  interface Window {
    HSStaticMethods: IStaticMethods;
  }
}
@Component({
    selector: 'app-root',
    imports: [WhoAmIComponent, ProjectsComponent, ContactComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'Samuel Petre Portfolio';

  constructor(private router: Router, private elementRef: ElementRef) {}

  ngOnInit(): void {
    if (typeof window !== 'undefined') {
      this.router.events.subscribe((event: Event) => {
        if (event instanceof NavigationEnd) {
          setTimeout(() => {
            window.HSStaticMethods?.autoInit?.();
          }, 100);
        }
      });
    }
  }

  scrollToSection(sectionId: string): void {
    const section = document.getElementById(sectionId);
    if (section) {
      // @ts-ignore
      const navbarHeight = document.querySelector('nav').clientHeight;
      const offset = section.getBoundingClientRect().top + window.scrollY - navbarHeight;
      window.scrollTo({ top: offset, behavior: 'smooth' });
    }
  }
}
