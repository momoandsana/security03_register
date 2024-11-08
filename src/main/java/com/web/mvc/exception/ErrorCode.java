package com.web.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATED(HttpStatus.BAD_REQUEST,"Duplicate ID","아이디가 중복임"),
    WRONG_PASS(HttpStatus.BAD_REQUEST,"wrong password","비밀번호가 오류");


    private final HttpStatus httpStatus;
    private final String title;
    private final String message;
}
