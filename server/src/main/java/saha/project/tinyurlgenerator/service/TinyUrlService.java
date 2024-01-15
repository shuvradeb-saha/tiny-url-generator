package saha.project.tinyurlgenerator.service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Hashtable;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import saha.project.tinyurlgenerator.errorhandler.exceptions.BadRequestException;
import saha.project.tinyurlgenerator.errorhandler.exceptions.NotFoundException;

/**
 * @created 12/4/23
 * @author Shuvradeb
 */
@Service
@Slf4j
public class TinyUrlService {
  public static Hashtable<String, String> originalUrlMap = new Hashtable<>();

  public String generateTinyUrl(final String longUrl) {
    if (!StringUtils.hasText(longUrl)) {
      throw new BadRequestException("URL must not be empty.");
    }
    val urlIdHash = generateHash(longUrl);
    log.info("LongUrl: {} \nShortUrl: {}", longUrl, urlIdHash);
    originalUrlMap.put(urlIdHash, longUrl);
    return String.valueOf(URI.create("http://localhost:8080/api/v1/redirect/%s".formatted(urlIdHash)));
  }

  public void redirect(final String urlHash, final HttpServletResponse response)
      throws IOException {
    val originalUrl = originalUrlMap.get(urlHash);
    if (originalUrl == null) {
      throw new NotFoundException("No URL is associated with the request.");
    }
    response.sendRedirect(originalUrl);
  }

  private String generateHash(final String originalUrl) {
    try {
      val messageDigest = MessageDigest.getInstance("SHA-224");
      byte[] hash = messageDigest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().withoutPadding().encodeToString(hash).substring(0, 8);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
