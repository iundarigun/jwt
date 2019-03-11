package br.com.devcave.jwt.controller;

import br.com.devcave.jwt.domain.request.UserRequest;
import br.com.devcave.jwt.security.AuthData;
import br.com.devcave.jwt.security.JwtTokenProvider;
import br.com.devcave.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class UserController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody UserRequest user){
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/register")
    public HttpEntity<UserRequest> test(){
        return ResponseEntity.ok(new UserRequest());
    }


    @PostMapping("/login")
    @Transactional(readOnly = true)
    public ResponseEntity login(@Valid @RequestBody AuthData data) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
            String token = jwtTokenProvider.createToken(data.getEmail(), userService.findByEmail(data.getEmail()).getRoleList());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", data.getEmail());
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }
}
