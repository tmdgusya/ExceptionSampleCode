package me.roach.demobootweb.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("1000", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED("1001", HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),
    FORBIDDEN("1002", HttpStatus.FORBIDDEN, "권한이 없습니다."),
    NOT_FOUND("1003", HttpStatus.NOT_FOUND, "찾을 수 없습니다."),
    METHOD_NOT_ALLOWED("1004", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드입니다."),
    UNKNOWN("1005", HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버에러");

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;

}
