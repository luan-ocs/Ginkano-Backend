package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.Group;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroupResponse implements BodyResponse{

    Group group;
}
