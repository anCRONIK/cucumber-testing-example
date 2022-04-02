package net.ancronik.samples.cucumber.data.repository;

import lombok.NonNull;
import net.ancronik.samples.cucumber.data.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link Note}
 *
 * @author Nikola Presecki
 */
@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    @Query(value = "SELECT n FROM Note n INNER JOIN n.author a WHERE a.username=:username",
            countQuery = "SELECT COUNT(n.id) FROM Note n JOIN n.author a WHERE a.username=:username")
    Page<Note> findAllByAuthorUsername(@NonNull @Param("username") String username, Pageable pageable);

    @Query("SELECT n FROM Note n JOIN n.author a WHERE n.id=:id AND a.username=:username")
    Optional<Note> findByIdAndAuthorUsername(@NonNull @Param("id") Long id, @NonNull @Param("username") String username);

    @Modifying
    @Query("DELETE FROM Note n WHERE n.id=:id AND n.author.username=:username")
    void deleteByIdAndAuthorUsername(@NonNull @Param("id") Long id, @NonNull @Param("username") String username);

}
