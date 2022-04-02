package net.ancronik.samples.cucumber;

import net.ancronik.samples.cucumber.data.model.Author;
import net.ancronik.samples.cucumber.data.model.Note;
import net.ancronik.samples.cucumber.data.repository.AuthorRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Set;

/**
 * Main application.
 *
 * @author Nikola Presecki
 */
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Bean
    public ApplicationRunner initializer(AuthorRepository repository, PasswordEncoder passwordEncoder) {
        Author testAuthor = new Author(null, "user", passwordEncoder.encode("user"), null, null);
        testAuthor.setNotes(Set.of(new Note(null, "some defaultNote", null, false, testAuthor)));

        return args -> repository.saveAll(Arrays.asList(
                new Author(null, "random", passwordEncoder.encode("random"), null, null),
                testAuthor
        ));
    }

}
