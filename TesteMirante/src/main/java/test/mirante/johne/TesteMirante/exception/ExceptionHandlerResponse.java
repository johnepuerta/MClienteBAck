package test.mirante.johne.TesteMirante.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import test.mirante.johne.TesteMirante.rest.ErrorDTO;

@ControllerAdvice
public class ExceptionHandlerResponse {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<String>> processUnmergeException(final MethodArgumentNotValidException ex) {

       List list = ex.getBindingResult().getAllErrors().stream()
               .map(fieldError -> fieldError.getDefaultMessage())
               .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<List<String>> processUnmergeException(final ConstraintViolationException ex) {

       List list = ex.getConstraintViolations().stream()
               .map(fieldError -> fieldError.getMessage())
               .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }



	@ExceptionHandler(GenericException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> processOptimisticLockException(final RuntimeException ex) {
       return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
