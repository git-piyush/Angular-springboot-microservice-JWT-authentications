import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  standalone: false,
  
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
  toggleDarkMode() {
    document.documentElement.classList.toggle('dark');
  }
}
