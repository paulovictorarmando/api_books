package pauloarmando.api_books.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pauloarmando.api_books.DTO.BookDTO;
import pauloarmando.api_books.exception.ApiBooksExceptions;
import pauloarmando.api_books.service.BookService;

import java.util.List;

@RestController
public class BookController {
        /*
            /api/books/delete      -       delete
            /api/books/update      -       update
            /api/books/search      -       search
            /api/books             -       home
        */
    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDTO>> home(){
        return ResponseEntity.ok(bookService.home());
    }

    @PostMapping("/api/books/create")
    public ResponseEntity<?> create(@RequestBody @Valid BookDTO dto) {
        try{
            bookService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ApiBooksExceptions e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/books/delete")
    public ResponseEntity<?> delete(@RequestBody String isbn)
    {
        try{
            bookService.delete(isbn);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ApiBooksExceptions e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/api/books/search")
    public ResponseEntity<?> search(@RequestBody String isbn){
        try{
            bookService.search(isbn);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ApiBooksExceptions e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/api/books/update")
    public ResponseEntity<?> update(@RequestBody @Valid BookDTO dto)
    {
        try{
            bookService.update(dto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ApiBooksExceptions e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
