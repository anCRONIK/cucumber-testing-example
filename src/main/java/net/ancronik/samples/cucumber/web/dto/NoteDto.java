package net.ancronik.samples.cucumber.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Notes dto for view.
 *
 * @author Nikola Presečki
 */
@Data
public class NoteDto {

    private Long id;

    private String text;

    private LocalDateTime dateCreated;
}
