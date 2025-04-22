package pauloarmando.api_books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pauloarmando.api_books.DTO.BookDTO;
import pauloarmando.api_books.exception.ApiBooksExceptions;
import pauloarmando.api_books.model.BookModel;
import pauloarmando.api_books.repository.BookRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /*
        /api/books/delete      -       delete
        /api/books/update      -       update
        /api/books/search      -       search
        /api/books             -       home
    */
    public List<BookDTO> home() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new BookDTO(
                        book.getTitle(),
                        book.getAuthor(),
                        book.getRelease(),
                        book.getId(),
                        book.getGender(),
                        book.getPublisher(),
                        book.getEdition()
                ))
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO bookDTO) {
        Optional<BookModel> bookCopy = bookRepository.findById(bookDTO.isbn());
        if (bookCopy.isPresent())
            throw new ApiBooksExceptions("Book already exists");
        BookModel newBookModel = new BookModel();
        newBookModel.setTitle(bookDTO.title());
        newBookModel .setAuthor(bookDTO.author());
        newBookModel .setId(bookDTO.isbn());
        newBookModel .setRelease(bookDTO.release());
        newBookModel .setGender(bookDTO.gender());
        newBookModel .setPublisher(bookDTO.publisher());
        newBookModel .setEdition(bookDTO.edition());
        bookRepository.save(newBookModel );
        return bookDTO;
    }

    public BookDTO update(BookDTO bookDTO) {
        Optional<BookModel> bookCopy = bookRepository.findById(bookDTO.isbn());
        if(bookCopy.isPresent()) {
            BookModel newBookModel = bookCopy.get();
            newBookModel.setTitle(bookDTO.title());
            newBookModel.setAuthor(bookDTO.author());
            newBookModel .setId(bookDTO.isbn());
            newBookModel.setRelease(bookDTO.release());
            newBookModel.setGender(bookDTO.gender());
            newBookModel.setPublisher(bookDTO.publisher());
            newBookModel.setEdition(bookDTO.edition());
            bookRepository.save(newBookModel);
            return bookDTO;
        }
        throw new ApiBooksExceptions("Book does not exists");
    }

    public BookDTO search(String id)
    {
        Optional<BookModel> bookCopy = bookRepository.findById(id);
        if(bookCopy.isPresent()) {
            return(new BookDTO(
                    bookCopy.get().getTitle(),
                    bookCopy.get().getAuthor(),
                    bookCopy.get().getRelease(),
                    bookCopy.get().getId(),
                    bookCopy.get().getGender(),
                    bookCopy.get().getPublisher(),
                    bookCopy.get().getEdition()
            ));
        }
        throw new ApiBooksExceptions("Book does not exists");
    }

    public void delete(String id)
    {
        Optional<BookModel> bookCopy = bookRepository.findById(id);
        if(bookCopy.isPresent()) {
            bookRepository.delete(bookCopy.get());
            return;
        }
        throw new ApiBooksExceptions("Book does not exists");
    }
}
