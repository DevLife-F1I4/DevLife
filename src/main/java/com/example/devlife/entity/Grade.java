package com.example.devlife.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {
    F1(1), F2(2), F3(3);

    private int value;
    public void updateValue(int newValue) {
        this.value = newValue;
    }
}
