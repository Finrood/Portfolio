import {Component, ElementRef} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet, Event} from '@angular/router';
import {WhoAmIComponent} from "./who-am-i/who-am-i.component";
import {ProjectsComponent} from "./projects/projects.component";
import {ContactComponent} from "./contact/contact.component";

import { IStaticMethods } from 'preline/preline';

declare global {
  interface Window {
    HSStaticMethods: IStaticMethods;
  }
}
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WhoAmIComponent, ProjectsComponent, ContactComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Samuel Petre Portfolio';

  constructor(private router: Router, private elementRef: ElementRef) {

  }

  ngOnInit() {
    if (typeof window !== 'undefined') {
      this.router.events.subscribe((event: Event) => {
        if (event instanceof NavigationEnd) {
          setTimeout(() => {
            window.HSStaticMethods.autoInit();
          }, 100);
        }
      });
    }
  }

  scrollToSection(sectionId: string): void {
    const section = this.elementRef.nativeElement.querySelector('#' + sectionId);
    if (section) {
      // @ts-ignore
      const navbarHeight = document.querySelector('nav').clientHeight;
      const offset = section.getBoundingClientRect().top + window.scrollY - navbarHeight;
      window.scrollTo({ top: offset, behavior: 'smooth' });
    }
  }

  navigateToSection(sectionId: string): void {
    this.router.navigate([], { fragment: sectionId });
    this.scrollToSection(sectionId);
  }
}
