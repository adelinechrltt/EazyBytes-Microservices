package com.adeline.cards.exceptions;

import com.adeline.cards.dtos.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<ErrorResponseDto> handleGlobalException(){

    }
}
