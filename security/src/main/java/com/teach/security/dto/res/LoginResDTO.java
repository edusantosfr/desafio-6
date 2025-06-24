package com.teach.security.dto.res;

public record LoginResDTO(
        String type, //Bearer
        String token,
        Long expiresAt
) {
}
