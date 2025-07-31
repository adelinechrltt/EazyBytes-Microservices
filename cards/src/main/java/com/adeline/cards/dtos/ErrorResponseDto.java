package com.adeline.cards.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API Path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error that occured"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message describing the error occurence"
    )
    private String errorMessage;

    @Schema(
            description = "Time of the error"
    )
    private LocalDateTime errorTime;

}
