package br.com.book.service.service;

import br.com.book.service.domain.Book;
import br.com.book.service.dto.BookPatchRequest;
import br.com.book.service.enums.Status;
import br.com.book.service.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Book create(Book newBook) {
        return repository.save(newBook);
    }

    public void update(Long id, Book newBook) {

        repository.findById(id)
                .map(pessoa -> repository.save(newBook))
                .orElseThrow(EntityNotFoundException::new);
    }

    public void patchStatus(String isbn, BookPatchRequest request) throws EntityNotFoundException {

        repository.findByIsbn(isbn)
                .map(book -> {
                    book.setStatus(request.status());

                    return repository.save(book);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    public Boolean isDisponvelByIsbn(final String isbn) throws EntityNotFoundException {

        final Book book = repository.findByIsbn(isbn)
                .orElseThrow(EntityNotFoundException::new);

        return Status.DISPONIVEL.equals(book.getStatus());
    }
}
