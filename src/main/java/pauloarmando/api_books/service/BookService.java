package pauloarmando.api_books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pauloarmando.api_books.DTO.BookDTO;
import pauloarmando.api_books.exception.ApiBooksExceptions;
import pauloarmando.api_books.model.BookModel;
import pauloarmando.api_books.repository.BookRepository;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /*
        ./api/books/create      -       create
        ./api/books/delete      -       delete
        ./api/books/update      -       update
        ./api/books             -       home
    */
    public List<BookModel> home()
    {
        return bookRepository.findAll();
    }
    public BookDTO create(BookDTO bookDTO) {
        Optional<BookModel> bookCopy = bookRepository.findByIsbn(bookDTO.isbn());
        if (bookCopy.isPresent())
            throw new ApiBooksExceptions("Book already exists");
        BookModel newBookModel = new BookModel();
        newBookModel.setTitle(bookDTO.title());
        newBookModel .setAuthor(bookDTO.author());
        newBookModel .setIsbn(bookDTO.isbn());
        newBookModel .setRelease(bookDTO.release());
        newBookModel .setGender(bookDTO.gender());
        newBookModel .setPublisher(bookDTO.publisher());
        newBookModel .setEdition(bookDTO.edition());
        bookRepository.save(newBookModel );
        return bookDTO;
    }
    public BookDTO update(BookDTO bookDTO) {
        Optional<BookModel> bookcopy = bookRepository.findByIsbn(bookDTO.isbn());
        if(bookcopy.isPresent()) {
            BookModel newBookModel = new BookModel();
            newBookModel.setTitle(bookDTO.title());
            newBookModel.setAuthor(bookDTO.author());
            newBookModel.setIsbn(bookDTO.isbn());
            newBookModel.setRelease(bookDTO.release());
            newBookModel.setGender(bookDTO.gender());
            newBookModel.setPublisher(bookDTO.publisher());
            newBookModel.setEdition(bookDTO.edition());
            bookRepository.save(newBookModel);
            return bookDTO;
        }
        throw new ApiBooksExceptions("Book does not exists");
    }
    public void delete(Long id)
    {
        Optional<BookModel> bookCopy = bookRepository.findById(id);
        if(bookCopy.isPresent()) {
            bookRepository.delete(bookCopy.get());
            return;
        }
        throw new ApiBooksExceptions("Book does not exists");
    }
    public void delete(String isbn)
    {
        Optional<BookModel> bookCopy = bookRepository.findByIsbn(isbn);
        if(bookCopy.isPresent()) {
            bookRepository.delete(bookCopy.get());
            return;
        }
        throw new ApiBooksExceptions("Book does not exists");
    }
}
