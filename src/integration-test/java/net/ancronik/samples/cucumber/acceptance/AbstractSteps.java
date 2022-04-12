package net.ancronik.samples.cucumber.acceptance;

import net.ancronik.samples.cucumber.data.repository.AuthorRepository;
import net.ancronik.samples.cucumber.data.repository.NoteRepository;
import net.ancronik.samples.cucumber.web.dto.JwtRequest;
import net.ancronik.samples.cucumber.web.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AbstractSteps {

    protected static final String TEST_USER = "user";

    protected static ResponseResults latestResponse = null;

    protected static String token;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected NoteRepository noteRepository;


    protected Map<String, String> generateDefaultHttpHeaders() {
        Map<String, String> headers = new HashMap<>();

        if (token == null) {
            token = getToken();
        }

//        headers.put(HttpHeaders.AUTHORIZATION, HttpHeaders.encodeBasicAuth(TEST_USER, "user", StandardCharsets.UTF_8));
        headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return headers;
    }

    private String getToken() {
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername(TEST_USER);
        jwtRequest.setPassword("user");

        JwtResponse jwtResponse = testRestTemplate.postForObject("/authenticate", jwtRequest, JwtResponse.class);

        return jwtResponse.getToken();
    }
}