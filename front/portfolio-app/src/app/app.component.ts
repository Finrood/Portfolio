import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {WhoAmIComponent} from "./who-am-i/who-am-i.component";
import {ProjectsComponent} from "./projects/projects.component";
import {ContactComponent} from "./contact/contact.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WhoAmIComponent, ProjectsComponent, ContactComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Samuel Petre Portfolio';
}
