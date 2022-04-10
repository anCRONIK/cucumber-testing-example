package net.ancronik.samples.cucumber.web.dto;

import lombok.*;

/**
 * Dto for auth request.
 *
 * @author Nikola Presečki
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class JwtRequest {

    @ToString.Include
    private String username;

    private String password;

}