import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  hide = true;
  loginForm: FormGroup;

  constructor(private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
    console.log('LoginComponent constructor called');
  }
  ngOnInit(): void {
    console.log('LoginComponent ngOnInit');
  }
  
  get usernameFormControl() {
    return this.loginForm.get('username');
  }

  get passwordFormControl() {
    return this.loginForm.get('password');
  }

  login() {
    const username = this.loginForm.get('username')?.value;
    const password = this.loginForm.get('password')?.value;

    // Perform login logic here

    // this.router.navigate(['/dashboard']);
    this.router.navigateByUrl('/home');
  }
}

