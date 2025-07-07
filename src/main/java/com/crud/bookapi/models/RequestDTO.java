package com.crud.bookapi.models;

import lombok.Data;

@Data
public class RequestDTO<T> {
    private T Data;
}
