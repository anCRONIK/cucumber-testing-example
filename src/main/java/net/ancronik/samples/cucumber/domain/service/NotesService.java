package net.ancronik.samples.cucumber.domain.service;

import lombok.NonNull;
import net.ancronik.samples.cucumber.application.exception.DataDoesNotExistException;
import net.ancronik.samples.cucumber.data.model.Note;
import net.ancronik.samples.cucumber.web.dto.CreateNoteRequest;
import net.ancronik.samples.cucumber.web.dto.NoteDto;
import net.ancronik.samples.cucumber.web.dto.UpdateNoteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface for {@link Note}
 *
 * @author Nikola Presečki
 */
public interface NotesService {

    /**
     * Method for fetching all notes for user.
     *
     * @param pageable pageable
     * @return page
     */
    Page<NoteDto> getAllNotesForAuthenticatedUser(@NonNull Pageable pageable);

    /**
     * Method for fetching specific note.
     *
     * @param id note id
     * @return note
     */
    NoteDto getAllNoteForAuthenticatedUser(@NonNull Long id) throws DataDoesNotExistException;

    /**
     * Method for creating new note.
     *
     * @param request request
     * @return
     */
    NoteDto createNoteForAuthenticatedUser(@NonNull CreateNoteRequest request);

    /**
     * Method for updating note which author should be authenticated user.
     *
     * @param id      id
     * @param request update request
     * @throws DataDoesNotExistException if note with given id does not exist
     * @return
     */
    NoteDto updateNoteForAuthenticatedUser(@NonNull Long id, @NonNull UpdateNoteRequest request) throws DataDoesNotExistException;

    /**
     * Method for deleting note which author should be authenticated user.
     *
     * @param id note
     */
    void deleteNoteForAuthenticatedUser(@NonNull Long id);

}
