package daniyar.hackaton.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import daniyar.hackaton.dto.ErrorDto;
import daniyar.hackaton.exception.UserAlreadyExistsException;
import daniyar.hackaton.exception.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorDto> handleIllegalArgument(HttpServletRequest request, IllegalArgumentException e) {
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(ErrorDto.builder()
                                                .timestamp(new Date())
                                                .status(HttpStatus.BAD_REQUEST.value())
                                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                .path(request.getServletPath())
                                                .message(e.getMessage())
                                                .build());
        }

        @ExceptionHandler({ UserNotFoundException.class })
        public ResponseEntity<?> handleNotFound(HttpServletRequest request, RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                ErrorDto.builder()
                                                .timestamp(new Date())
                                                .status(HttpStatus.NOT_FOUND.value())
                                                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                                                .path(request.getServletPath())
                                                .message(e.getMessage())
                                                .build());
        }

        @ExceptionHandler({ UserAlreadyExistsException.class })
        public ResponseEntity<?> handleAlreadyExists(HttpServletRequest request, RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ErrorDto.builder()
                                                .timestamp(new Date())
                                                .status(HttpStatus.BAD_REQUEST.value())
                                                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                .path(request.getServletPath())
                                                .message(e.getMessage())
                                                .build());
        }

        @ExceptionHandler({ ExpiredJwtException.class })
        public ResponseEntity<?> handleExpiredToken(HttpServletRequest request, ExpiredJwtException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ErrorDto.builder()
                                                .timestamp(new Date())
                                                .status(HttpStatus.UNAUTHORIZED.value())
                                                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                                                .path(request.getServletPath())
                                                .message(e.getMessage())
                                                .build());
        }
}