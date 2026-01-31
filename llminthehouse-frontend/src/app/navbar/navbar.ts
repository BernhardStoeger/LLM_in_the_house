import { Component } from '@angular/core';
import {MatToolbar} from '@angular/material/toolbar';
import {RouterLink} from '@angular/router';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'llminthehouse-navbar',
  imports: [
    MatToolbar,
    RouterLink,
    MatButton,
  ],
  templateUrl: './navbar.html'
})
export class Navbar {

}
