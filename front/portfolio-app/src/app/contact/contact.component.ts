import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  imports: [
    FormsModule
],
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  @Output() componentLoaded = new EventEmitter<boolean>();

  ngOnInit(): void {
    // Since contact component doesn't fetch data, it can emit loaded immediately
    this.componentLoaded.emit(true);
    console.log('ContactComponent: componentLoaded emitted.');
  }
  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log('Form Submitted!', form.value);
      // Here you would typically send the form data to a backend service
      form.reset();
    }
  }
}
