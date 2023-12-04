package saha.project.tinyurlgenerator.errorhandler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @created 12/4/23
 * @author Shuvradeb
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ApiError {

  private int status;
  private String messages;
}
