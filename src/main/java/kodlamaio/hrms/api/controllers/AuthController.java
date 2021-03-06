package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.auth.EmployerRegister;
import kodlamaio.hrms.entities.auth.JobSeekerRegister;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("registerForJobSeeker")
    public Result registerForJobSeeker(@RequestBody JobSeekerRegister jobSeekerRegister) {
        return this.authService.registerForJobSeeker(jobSeekerRegister);
    }

    @PostMapping("registerForEmployer")
    public Result registerForEmployer(@RequestBody EmployerRegister employerRegister) {
        return this.authService.registerForEmployer(employerRegister);
    }

    @PostMapping("login")
    public Result loginForJobSeeker(@RequestBody JobSeeker jobSeeker) {
        return this.authService.loginForJobSeeker(jobSeeker);
    }

    @GetMapping("activateEmail")
    @ResponseBody
    public Result activateEmail(@RequestParam String activationCode) {
        return this.authService.activateEmail(activationCode);
    }
}
