package saha.project.tinyurlgenerator.errorhandler.exceptions;

/**
 * @created 12/4/23
 * @author Shuvradeb
 */
public class BadRequestException extends RuntimeException {
  public BadRequestException(final String message) {
    super(message);
  }
}
