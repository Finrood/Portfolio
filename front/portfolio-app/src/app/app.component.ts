import { Component, OnInit } from '@angular/core';
import { WhoAmIComponent } from "./who-am-i/who-am-i.component";
import { ProjectsComponent } from "./projects/projects.component";
import { ContactComponent } from "./contact/contact.component";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [
    WhoAmIComponent,
    ProjectsComponent,
    ContactComponent
],
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  loading = true;
  mobileMenuOpen = false;

  whoAmILoaded = false;
  projectsLoaded = false;
  contactLoaded = false;

  ngOnInit() {
    // Initial check, though components will update these flags
    this.checkAllComponentsLoaded();
  }

  onWhoAmILoaded(loaded: boolean) {
    this.whoAmILoaded = loaded;
    console.log(`WhoAmILoaded: ${loaded}`);
    this.checkAllComponentsLoaded();
  }

  onProjectsLoaded(loaded: boolean) {
    this.projectsLoaded = loaded;
    console.log(`ProjectsLoaded: ${loaded}`);
    this.checkAllComponentsLoaded();
  }

  onContactLoaded(loaded: boolean) {
    this.contactLoaded = loaded;
    console.log(`ContactLoaded: ${loaded}`);
    this.checkAllComponentsLoaded();
  }

  private checkAllComponentsLoaded() {
    this.loading = !(this.whoAmILoaded && this.projectsLoaded && this.contactLoaded);
    console.log(`All components loaded: ${!this.loading}`);
  }

  scrollToSection(sectionId: string): void {
    const section = document.getElementById(sectionId);
    if (section) {
      section.scrollIntoView({ behavior: 'smooth' });
    }
  }
}