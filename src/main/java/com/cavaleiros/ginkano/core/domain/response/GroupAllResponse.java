package com.cavaleiros.ginkano.core.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GroupAllResponse implements BodyResponse{
    List<GroupResponse> data;
    String datetime;
}
