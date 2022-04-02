package net.ancronik.samples.cucumber.data.repository;

import lombok.NonNull;
import net.ancronik.samples.cucumber.data.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link Author}
 *
 * @author Nikola Presecki
 */
@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Optional<Author> findByUsername(@NonNull String username);
}
