package saha.project.tinyurlgenerator.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import saha.project.tinyurlgenerator.service.TinyUrlService;

import java.io.IOException;

/**
 * @created 12/3/23
 * @author Shuvradeb
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class TinyUrlController {

  private final TinyUrlService tinyUrlService;

  public TinyUrlController(final TinyUrlService tinyUrlService) {
    this.tinyUrlService = tinyUrlService;
  }

  @RequestMapping(value = "/generate", method = RequestMethod.POST)
  public String shortenUrl(final @RequestBody String longUrl) {
    return tinyUrlService.generateTinyUrl(longUrl);
  }

  @RequestMapping(value = "/redirect/{uniqueHashValue}", method = RequestMethod.GET)
  public void redirect(
      final @PathVariable("uniqueHashValue") String uniqueHashValue,
      final HttpServletResponse response)
      throws IOException {
    tinyUrlService.redirect(uniqueHashValue, response);
  }
}
