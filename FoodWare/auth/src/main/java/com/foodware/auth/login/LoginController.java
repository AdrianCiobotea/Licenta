package com.foodware.auth.login;

import com.foodware.auth.login.loginToken.LoginTokenService;
import com.foodware.auth.user.User;
import com.foodware.auth.user.UserDetails;
import com.foodware.auth.user.UserRole;
import com.foodware.auth.user.UserService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "login")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {


    private UserService userService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody User userParam) {
        UserDetails user = userService.verifyLogin(userParam.getEmail(), userParam.getPassword());

        if (user == null) {
            return new ResponseEntity<String>("bye bye", HttpStatus.UNAUTHORIZED);
        }

        try {
            if (user.getUserRole().equals(UserRole.CLIENT)) {
                return new ResponseEntity<String>(JSONObject.quote(user.getUserRole().toString()), HttpStatus.OK);
            } else if (user.getUserRole().equals(UserRole.ADMIN)) {
                return new ResponseEntity<String>(JSONObject.quote(user.getUserRole().toString()), HttpStatus.ACCEPTED);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
    }

}
