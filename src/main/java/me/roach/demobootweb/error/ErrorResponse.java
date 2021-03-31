package me.roach.demobootweb.error;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;
    private LocalDateTime time;
    private List<FieldError> errors;
    private UUID logId;

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult, UUID logId) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getReason())
                .time(LocalDateTime.now())
                .errors(FieldError.of(bindingResult))
                .logId(logId)
                .build();
    }

}
