package br.com.book.service.domain;

import br.com.book.service.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String isbn;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DISPONIVEL;
}
