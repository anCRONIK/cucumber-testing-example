package net.ancronik.samples.cucumber.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.ancronik.samples.cucumber.application.exception.DataDoesNotExistException;
import net.ancronik.samples.cucumber.domain.service.NotesService;
import net.ancronik.samples.cucumber.web.dto.CreateNoteRequest;
import net.ancronik.samples.cucumber.web.dto.NoteDto;
import net.ancronik.samples.cucumber.web.dto.UpdateNoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for {@link }
 */
@RestController
@RequestMapping("/api/notes")
@Slf4j
public class NotesController {

    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<NoteDto> getNotes(@PageableDefault Pageable pageable) {

        return notesService.getAllNotesForAuthenticatedUser(pageable);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto getNote(@PathVariable Long id) throws DataDoesNotExistException {

        return notesService.getAllNoteForAuthenticatedUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> createNote(@RequestBody CreateNoteRequest request) {
        NoteDto note = notesService.createNoteForAuthenticatedUser(request);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(note.getId()).toUri()).build();
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto updateNote(@PathVariable Long id, @RequestBody UpdateNoteRequest request) throws DataDoesNotExistException {

        return notesService.updateNoteForAuthenticatedUser(id, request);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {

        notesService.deleteNoteForAuthenticatedUser(id);

        return ResponseEntity.ok().build();
    }

}
