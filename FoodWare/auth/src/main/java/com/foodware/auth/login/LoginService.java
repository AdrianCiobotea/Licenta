//package com.foodware.auth.login;
//
//import com.foodware.auth.user.UserDetails;
//import com.foodware.auth.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LoginService implements AuthenticationProvider {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        String email = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        UserDetails user= userService.loadUserByEmail(email);
//        if(user == null) {
//        throw new BadCredentialsException("No user with this email was found");
//        }
//            if (passwordEncoder.matches(pwd, customer.get(0).getPwd())) {
//                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(customer.get(0).getAuthorities()));
//            } else {
//                throw new BadCredentialsException("Invalid password!");
//            }
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authenticationType) {
//        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//}
