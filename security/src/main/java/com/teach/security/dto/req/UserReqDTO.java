package com.teach.security.dto.req;

import com.teach.security.domain.Role;

public record UserReqDTO(
        String username,
        String password,
        Role role
) {
}
