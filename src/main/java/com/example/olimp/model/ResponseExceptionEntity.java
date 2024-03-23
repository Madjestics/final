package com.example.olimp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseExceptionEntity {
    private int status;
    private String reason;
}
