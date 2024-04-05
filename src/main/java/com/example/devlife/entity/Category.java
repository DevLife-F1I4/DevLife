package com.example.devlife.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    PET("개발자네 개팔자"), DEVTOOL("개발자 도구"), GOURMET("고독한 개발자");
    private final String description;
}
