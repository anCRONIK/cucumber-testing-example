package net.ancronik.samples.cucumber.domain.mapper;

import lombok.NonNull;
import net.ancronik.samples.cucumber.data.model.Author;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static net.ancronik.samples.cucumber.application.Constants.USER_SECURITY_ROLE;

/**
 * Mapper for creating {@link UserDetails} from {@link Author}.
 *
 * @author Nikola Presecki
 */
@Component
public class AuthorToUserDetailsMapper implements Mapper<Author, UserDetails> {


    @Override
    public UserDetails map(@NonNull Author user) {

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(USER_SECURITY_ROLE).build();
    }
}
