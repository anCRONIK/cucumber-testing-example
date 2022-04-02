package net.ancronik.samples.cucumber.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity representing the notes author.
 * <p>
 * In our case this is also the user itself so it is reused for authorization.
 *
 * @author Nikola Presecki
 */
@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    @NonNull
    @NotBlank
    @Size(max = 20)
    @EqualsAndHashCode.Include
    private String username;

    @Column(name = "password_hash", nullable = false)
    @NonNull
    @NotBlank
    @Size(max = 300)
    private String passwordHash;

    @CreationTimestamp
    @Column(name = "date_registered", updatable = false)
    private LocalDateTime dateRegistered;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_notes",
            joinColumns = @JoinColumn(name = "author"),
            inverseJoinColumns = @JoinColumn(name = "notes")
    )
    @ToString.Exclude
    private Set<Note> notes;
}
