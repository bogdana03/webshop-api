
package ax.ha.clouddevelopment.webshopapi.interfaces.controller;

import ax.ha.clouddevelopment.webshopapi.domain.service.NoteService;
import ax.ha.clouddevelopment.webshopapi.domain.service.WebshopService;
import ax.ha.clouddevelopment.webshopapi.interfaces.v1.webshop.NotesApi;
import ax.ha.clouddevelopment.webshopapi.interfaces.v1.webshop.model.Note;
import ax.ha.clouddevelopment.webshopapi.interfaces.v1.webshop.model.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller for the webshop API
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@RestController
public class WebshopApiController implements NotesApi {

    private final NoteService noteService;

    private final WebshopService webshopService;

    @Autowired
    public WebshopApiController(final NoteService noteService,
                                final WebshopService webshopService) {
        this.noteService = noteService;
        this.webshopService = webshopService;
    }

    @Override
    public ResponseEntity<List<NoteEntity>> getNotes() {
        return ResponseEntity.ok(noteService.getNotes().stream()
                .map(this::toNoteEntity)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<NoteEntity> createNote(final Note note) {
        return ResponseEntity.ok(toNoteEntity(noteService.addNote(toNote(note, UUID.randomUUID()))));
    }

    @Override
    public ResponseEntity<Void> deleteNote(final UUID noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<NoteEntity> getNote(final UUID noteId) {
        return ResponseEntity.ok(toNoteEntity(noteService.getNote(noteId)));
    }


    @Override
    public ResponseEntity<NoteEntity> updateNote(final UUID noteId,
                                                 final Note note) {
        return ResponseEntity.ok(toNoteEntity(noteService.updateNote(toNote(note, noteId))));
    }

    private NoteEntity toNoteEntity(final ax.ha.clouddevelopment.webshopapi.domain.model.Note note) {
        return new NoteEntity()
                .id(note.getId())
                .description(note.getDescription())
                .name(note.getName());
    }

    private ax.ha.clouddevelopment.webshopapi.domain.model.Note toNote(final Note product,
                                                                       final UUID uuid) {
        return ax.ha.clouddevelopment.webshopapi.domain.model.Note.builder()
                .id(uuid)
                .description(product.getDescription())
                .name(product.getName())
                .build();
    }
}