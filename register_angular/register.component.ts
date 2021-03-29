import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AlertService, UserService, AuthenticationService } from '@/_services';

@Component({ templateUrl: 'register.component.html' })
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private userService: UserService,
        private alertService: AlertService
    ) {

    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            npi: ['', Validators.required],
			email: ['', Validators.required],
		    address: ['', Validators.required],
            tele: ['', [Validators.required, Validators.minLength(10)]]
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        // reset alerts on submit
        this.alertService.clear();

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        this.userService.register(this.registerForm.value)
            .subscribe(
                data => {
                    this.alertService.success('Registration succeeded', true);
                    this.router.navigate(['/home']);
                },
                error => {
                    this.alertService.error(error);
                });
    }
}