package com.foodware.auth.user;

import com.foodware.auth.registration.token.ConfirmationToken;
import com.foodware.auth.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("No user in database with email " + email);
        else if (!user.getEnabled())
            throw new UsernameNotFoundException("No active user with login " + email);
        else {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail())!=null;
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        //TODO: SEND EMAIL
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public User verifyLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user !=null){
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            if(user.getPassword().equals(encodedPassword)){
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }

    }
}
