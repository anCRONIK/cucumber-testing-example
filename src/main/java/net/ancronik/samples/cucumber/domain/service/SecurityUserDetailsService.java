package net.ancronik.samples.cucumber.domain.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.ancronik.samples.cucumber.data.model.Author;
import net.ancronik.samples.cucumber.data.repository.AuthorRepository;
import net.ancronik.samples.cucumber.domain.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Implementation of {@link UserDetailsService}
 *
 * @author Nikola Presecki
 */
@Service
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    private final AuthorRepository authorRepository;

    private final Mapper<Author, UserDetails> basicUserToUserDetailsMapper;

    @Autowired
    public SecurityUserDetailsService(AuthorRepository authorRepository, Mapper<Author, UserDetails> authorUserDetailsMapper) {
        this.authorRepository = authorRepository;
        this.basicUserToUserDetailsMapper = authorUserDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        if (username.isBlank()) {
            throw new UsernameNotFoundException("username is blank");
        }
        LOG.debug("Searching for author {}", username);

        Author author = authorRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

        LOG.debug("User found {}", author);
        return basicUserToUserDetailsMapper.map(author);
    }

}