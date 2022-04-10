package net.ancronik.samples.cucumber.web.dto;

import lombok.*;

/**
 * Dto for token response.
 *
 * @author Nikola Presečki
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class JwtResponse {

	private final String token;
}