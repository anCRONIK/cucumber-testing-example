package net.ancronik.samples.cucumber.web.dto;

import lombok.*;

/**
 * Dto for token response.
 *
 * @author Nikola Presečki
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class JwtResponse {

	private String token;
}