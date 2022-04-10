package net.ancronik.samples.cucumber.domain.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.ancronik.samples.cucumber.application.exception.DataDoesNotExistException;
import net.ancronik.samples.cucumber.application.exception.UnauthorizedActionException;
import net.ancronik.samples.cucumber.data.model.Note;
import net.ancronik.samples.cucumber.data.repository.AuthorRepository;
import net.ancronik.samples.cucumber.data.repository.NoteRepository;
import net.ancronik.samples.cucumber.domain.mapper.Mapper;
import net.ancronik.samples.cucumber.domain.util.JwtTokenUtil;
import net.ancronik.samples.cucumber.web.dto.CreateNoteRequest;
import net.ancronik.samples.cucumber.web.dto.NoteDto;
import net.ancronik.samples.cucumber.web.dto.UpdateNoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link NotesService}.
 *
 * @author Nikola Presečki
 */
@Service
@Slf4j
public class NotesServiceImpl implements NotesService {

    private final NoteRepository noteRepository;

    private final AuthorRepository authorRepository;

    private final Mapper<Note, NoteDto> notesToNotesDtoMapper;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public NotesServiceImpl(NoteRepository noteRepository, AuthorRepository authorRepository, Mapper<Note, NoteDto> notesToNotesDtoMapper, JwtTokenUtil jwtTokenUtil) {
        this.noteRepository = noteRepository;
        this.authorRepository = authorRepository;
        this.notesToNotesDtoMapper = notesToNotesDtoMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public Page<NoteDto> getAllNotesForAuthenticatedUser(@NonNull Pageable pageable) {
        LOG.info("Fetching all notes for authenticated user with paging properties [{}]", pageable);

        return noteRepository.findAllByAuthorUsername(getLoggedInUsername(), pageable).map(notesToNotesDtoMapper::map);
    }

    @Override
    public NoteDto getAllNoteForAuthenticatedUser(@NonNull Long id) throws DataDoesNotExistException {
        LOG.info("Fetching note [{}] for authenticated user", id);

        return notesToNotesDtoMapper.map(noteRepository.findByIdAndAuthorUsername(id, getLoggedInUsername())
                .orElseThrow(() -> new DataDoesNotExistException("Note with id does not exists: " + id)));
    }

    @Transactional
    @SneakyThrows
    public NoteDto createNoteForAuthenticatedUser(@NonNull CreateNoteRequest request) {
        LOG.info("Creating new note");

        //FIXME create mappers for this one
        Note note = new Note();
        note.setText(request.getText());
        note.setAuthor(authorRepository.findByUsername(getLoggedInUsername()).orElseThrow(() -> new UnauthorizedActionException("author not found: " + getLoggedInUsername()))); //FIXME this should be internal exception

        noteRepository.save(note);
        return null;
    }

    @Transactional
    public NoteDto updateNoteForAuthenticatedUser(@NonNull Long id, @NonNull UpdateNoteRequest request) throws DataDoesNotExistException {
        LOG.info("Updating note with id: [{}]", id);

        Note note = noteRepository.findById(id).orElseThrow(() -> new DataDoesNotExistException("Note with id does not exists: " + id));

        if (note.getAuthor().getUsername().equals(getLoggedInUsername())) {
            LOG.error("MAJOR ISSUE! User [{}] tried to update note of another user. id: [{}] original author: [{}]",
                    getLoggedInUsername(), id, note.getAuthor().getUsername());
            throw new UnauthorizedActionException(getLoggedInUsername() + " tried to edit note of different user");
        }

        note.setEdited(true);
        note.setText(request.getText());

        return notesToNotesDtoMapper.map(noteRepository.save(note));
    }

    @Transactional
    public void deleteNoteForAuthenticatedUser(@NonNull Long id) {
        LOG.info("Deleting note with id: [{}]", id);

        noteRepository.deleteByIdAndAuthorUsername(id, getLoggedInUsername());
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //FIXME extract to another service
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
