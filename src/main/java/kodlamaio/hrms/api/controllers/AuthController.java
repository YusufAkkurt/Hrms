package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.auth.JobSeekerRegister;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public Result registerForJobSeeker(@RequestBody JobSeekerRegister jobSeekerRegister) {
        return this.authService.registerForJobSeeker(jobSeekerRegister);
    }

    @PostMapping("login")
    public Result loginForJobSeeker(@RequestBody JobSeeker jobSeeker) {
        return this.authService.loginForJobSeeker(jobSeeker);
    }
}
