package pauloarmando.api_books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pauloarmando.api_books.model.BookModel;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, String> {
}
