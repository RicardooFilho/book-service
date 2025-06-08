package br.com.book.service.dto;

import br.com.book.service.enums.Status;

public record BookPatchRequest(
        Status status
) {
}
