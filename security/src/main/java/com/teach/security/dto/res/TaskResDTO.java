package com.teach.security.dto.res;

import com.teach.security.domain.Status;

public record TaskResDTO (
        Long id,
        String titulo,
        String descricao,
        Status status
) {
}
