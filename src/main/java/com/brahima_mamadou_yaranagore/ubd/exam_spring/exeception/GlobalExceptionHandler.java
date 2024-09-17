package com.brahima_mamadou_yaranagore.ubd.exam_spring.exeception;
import lombok.extern.slf4j.Slf4j;
import com.brahima_mamadou_yaranagore.ubd.exam_spring.dto.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {
        log.error(" Erreur de Validation : {}", exception.getMessage(), exception);
        Map<String, String>erreurs = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            erreurs.put(fieldName, errorMessage);
        });

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value() + "")
                .message("Echec de la Validation ")
                .details(erreurs)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }




    @ExceptionHandler(value = { NoHandlerFoundException.class })
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException (NoHandlerFoundException exception) {

        ErrorResponseDTO response = new ErrorResponseDTO("404", "Ressource Introuvable, Veillez ressayer avec une URL valide ");
        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value()+ "")
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value() + "")
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        log.error(exception.getMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .code(HttpStatus.CONFLICT.value()+ "")
                .message(exception.getMessage())
                .build();
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);

    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO  handleGenericException(Exception exception) {
        log.error(exception.getMessage(), exception);
         return ErrorResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value() + "")
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(value = { ActivityNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleActivityNotFoundException(ActivityNotFoundException exception) {
        log.error(exception.getMessage(),exception);
        return ErrorResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value() + "")
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(value = { ProjectNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleProjectNotFoundException(ProjectNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value() + "")
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(value = { TaskNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleTaskNotFoundException(TaskNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value() + "")
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("Invalid JSON input: {}", exception.getMessage(), exception);

        String errorMessage = " Format d'entrée invalide . utiliser cette format 'AAAA-MM-JJ'.";

        // Check if the cause is a DateTimeParseException
        if (exception.getCause() instanceof DateTimeParseException) {
            errorMessage = "format de date non valide  . utiliser cette format 'AAAA-MM-JJ'.";
        }

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value() + "")
                .message(errorMessage)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

}