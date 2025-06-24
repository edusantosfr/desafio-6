package com.teach.security.dto.req;

import com.teach.security.domain.Status;

public record TaskReqDTO (
        String titulo,
        String descricao,
        Status status
) {
}
