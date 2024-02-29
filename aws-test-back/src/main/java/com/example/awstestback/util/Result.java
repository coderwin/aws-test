package com.example.awstestback.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Result<T> {

    private T data;     // 결과값
    private int code;   // 상태
    private String message; // 결과 메시지

    @Builder
    public Result(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    // TODO : front에서 data가 unll인지 undefined 인지 확인 필요
    //        -- null 이다.
    public static Result<Void> success() {

        Result<Void> result = Result.<Void>builder()
                .code(HttpStatus.OK.value())
                .build();

        return result;
    }

    public static <T> Result<T> success(T data, int code, String message) {

        Result<T> result = Result.<T>builder()
                .data(data)
                .code(code)
                .message(message)
                .build();

        return result;

    }


}
