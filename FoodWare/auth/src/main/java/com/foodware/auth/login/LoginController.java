package com.foodware.auth.login;

import com.foodware.auth.user.User;
import com.foodware.auth.user.UserService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "login")
@AllArgsConstructor
public class LoginController {

    private UserService userService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        //UserDetails user = userService.loadUserByUsername(email);
        User user = userService.verifyLogin(email, password);
        if (user != null) {
            try {

                if (user.getAuthorities().equals("CLIENT"))
                    return new ResponseEntity<String>(JSONObject.quote(user.getAuthorities().toString()), HttpStatus.OK);
                else if (user.getAuthorities().equals("ADMIN"))
                    return new ResponseEntity<String>(JSONObject.quote(user.getAuthorities().toString()), HttpStatus.ACCEPTED);
                else
                    throw new Exception();
            } catch (Exception e) {
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<String>(JSONObject.quote(user.getAuthorities().toString()), HttpStatus.OK);
    }

}
