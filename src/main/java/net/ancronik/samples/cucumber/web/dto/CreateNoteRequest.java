package net.ancronik.samples.cucumber.web.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Request to create notes.
 *
 * @author Nikola Presecki
 */
@Data
public class CreateNoteRequest {

    @NotBlank
    @Size(max = 10000)
    @NonNull
    private String text;

}
