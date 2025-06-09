package br.com.book.service.controller;

import br.com.book.service.domain.Book;
import br.com.book.service.dto.BookPatchRequest;
import br.com.book.service.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable(value = "id") Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/disponivel/{isbn}/isbn")
    public ResponseEntity<Boolean> isDisponivelByIsbn(@PathVariable String isbn) throws EntityNotFoundException {

        return ResponseEntity.ok(service.isDisponvelByIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid Book newBook) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(newBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Book newBook) {

        service.update(id, newBook);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/isbn/{isbn}")
    public ResponseEntity<Void> patchStatus(@PathVariable String isbn,
                                            @RequestBody BookPatchRequest request) throws EntityNotFoundException {
        service.patchStatus(isbn, request);
        return ResponseEntity.ok().build();
    }

}
