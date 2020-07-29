package com.example.http.result;

public interface IResult<T> {
    boolean isSuccess();

    int getCode();

    T data();
}
