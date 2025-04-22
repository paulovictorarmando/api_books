package pauloarmando.api_books.DTO;

public record BookDTO(
    String title,
    String author,
    Integer release,
    String isbn,
    String gender,
    String publisher,
    String edition) { }
