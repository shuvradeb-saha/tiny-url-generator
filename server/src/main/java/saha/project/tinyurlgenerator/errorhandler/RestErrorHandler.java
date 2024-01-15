package saha.project.tinyurlgenerator.errorhandler;

import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import saha.project.tinyurlgenerator.errorhandler.exceptions.BadRequestException;
import saha.project.tinyurlgenerator.errorhandler.exceptions.NotFoundException;
import saha.project.tinyurlgenerator.errorhandler.model.ApiError;

/**
 * @created 12/4/23
 * @author Shuvradeb
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ApiError> badRequestHandler(final BadRequestException ex) {
    val apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> notFoundHandler(final NotFoundException ex) {
    val apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }
}
