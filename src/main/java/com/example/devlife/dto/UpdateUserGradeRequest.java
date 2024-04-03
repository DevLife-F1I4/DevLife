package com.example.devlife.dto;

import com.example.devlife.entity.Grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserGradeRequest {
	Grade grade;
}
