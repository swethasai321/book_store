package com.bookstoreapplication.exception;

import com.bookstoreapplication.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookStoreException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception while parsing Rest request",errorMessage);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreExceptionHandler.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(BookStoreExceptionHandler exception){
        ResponseDTO responseDTO = new ResponseDTO("Exception while parsing Rest request",exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
