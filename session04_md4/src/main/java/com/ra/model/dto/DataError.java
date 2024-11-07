package com.ra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NonNull
@Getter
@Setter
public class DataError<T>{
    private T message;
    private int status;
}
