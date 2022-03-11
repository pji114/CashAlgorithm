package com.amore.cash.packet;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ExceptionResponse {
    private final int statusCode;
    private final String message;
    private final LocalDateTime dateTime;
}
