package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.Student;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentResponse implements BodyResponse{
    Student student;
}
