package pauloarmando.api_books.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookModel {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)  // Garanta que está buscando pelo campo ISBN
    private String id;
    private String title;
    private String author;
    private String release;
    private String gender;
    private String publisher;
    private String edition;
}
