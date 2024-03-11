import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {WhoAmIComponent} from "./who-am-i/who-am-i.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WhoAmIComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'portfolio-app';
}
