package pauloarmando.api_books.DTO;

import lombok.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record BookDTO(
        @NotBlank String title,
        @NotBlank String author,
        @NotNull  String release,
        @NotBlank String isbn,
        @NotBlank String gender,
        @NotBlank String publisher,
        @NotBlank String edition) { }
