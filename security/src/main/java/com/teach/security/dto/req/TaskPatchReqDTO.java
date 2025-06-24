package com.teach.security.dto.req;

import com.teach.security.domain.Status;

public record TaskPatchReqDTO (String descricao, Status status){

}
