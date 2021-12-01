package com.foodware.auth.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "register")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;


    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
