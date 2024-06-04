package power.debitservice.controller

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice




/**
 * Глобальные обработчики исключений
  */
@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler
    fun exceptionHandler(e: ImageNotFoundException)=
        ResponseEntity(ResponseErrorDto(e.message), HttpStatus.NOT_FOUND)

    @ExceptionHandler
    fun exceptionHandler(exception: Exception) =
        ResponseEntity
            .internalServerError()
            .contentType(MediaType.APPLICATION_JSON)
            .body(ResponseErrorDto(exception.message))

    @ExceptionHandler
    fun entityNotFoundExceptionHandler(exception: EntityNotFoundException) =
        ResponseEntity
            .badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .body(exception.message)
}