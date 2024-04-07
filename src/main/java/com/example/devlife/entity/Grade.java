package com.example.devlife.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {
    F1("F1"), F2("F2"), F3("F3");
    private final String description;

    @JsonCreator
    public static Grade from(String str){
        for (Grade grade : Grade.values()) {
            if(grade.getDescription().equals(str)){
                return grade;
            }
        }
        throw new IllegalArgumentException("not such grade: " + str);
    }
}
