package br.com.devcave.jwt.controller;

import br.com.devcave.jwt.domain.request.UserRequest;
import br.com.devcave.jwt.security.AuthData;
import br.com.devcave.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody UserRequest user) {
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/register")
    public HttpEntity<UserRequest> test() {
        return ResponseEntity.ok(new UserRequest());
    }


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthData data) {
        String token = userService.authenticateAndGenerateToken(data);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", data.getEmail());
        model.put("token", token);
        return ResponseEntity.ok(model);
    }
}
