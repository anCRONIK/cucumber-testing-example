package net.ancronik.samples.cucumber.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;

    private boolean edited;
}
