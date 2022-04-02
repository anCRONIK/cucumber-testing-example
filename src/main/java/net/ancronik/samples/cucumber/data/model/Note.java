package net.ancronik.samples.cucumber.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity representing user notes.
 *
 * @author Nikola Presečki
 */
@Entity
@Table(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 10000)
    @NonNull
    @ToString.Include
    private String text;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)
    @ToString.Include
    private LocalDateTime dateCreated;

    @Column
    private boolean edited;

    @ManyToOne
    @NonNull
    private Author author;

    @ToString.Include(name = "author")
    private String getAuthorName() {

        return author.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (getId() != null && note.getId() != null) {
            return getId().equals(note.id);
        }

        return getDateCreated().equals(note.getDateCreated()) && getAuthorName().equals(note.getAuthorName()) && getText().equals(note.getText());
    }

    @Override
    public int hashCode() {
        return null != getId() ? getId().hashCode() : getText().hashCode() + getAuthorName().hashCode()
                + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
    }
}
