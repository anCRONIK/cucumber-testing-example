package net.ancronik.samples.cucumber.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.ancronik.samples.cucumber.domain.util.JwtTokenUtil;
import net.ancronik.samples.cucumber.web.dto.JwtRequest;
import net.ancronik.samples.cucumber.web.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * Used from: https://www.javainuse.com/spring/boot-jwt
 */
@RestController
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        LOG.debug("New auth request: {}", request);
        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}