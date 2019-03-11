package br.com.devcave.jwt.security;

import br.com.devcave.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public String authenticateAndGenerateToken(AuthData data) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
            return jwtTokenProvider.createToken(data.getEmail(), userService.findByEmail(data.getEmail()).getRoleList());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }


}
