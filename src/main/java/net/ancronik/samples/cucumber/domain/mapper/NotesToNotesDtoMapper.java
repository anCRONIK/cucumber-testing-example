package net.ancronik.samples.cucumber.domain.mapper;

import lombok.NonNull;
import net.ancronik.samples.cucumber.data.model.Note;
import net.ancronik.samples.cucumber.web.dto.NoteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper for {@link Note} and {@link NoteDto}.
 *
 * @author Nikola Presečki
 */
@Component
public class NotesToNotesDtoMapper implements Mapper<Note, NoteDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public NotesToNotesDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public NoteDto map(@NonNull Note note) {

        return modelMapper.map(note, NoteDto.class);
    }
}
