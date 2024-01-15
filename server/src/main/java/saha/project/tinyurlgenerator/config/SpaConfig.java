package saha.project.tinyurlgenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @created 1/15/24
 * @author Shuvradeb
 */
@Configuration
public class SpaConfig implements WebMvcConfigurer {
  /**
   * This method basically serves the index.html for any page requested. Basically makes our spring
   * boot server capable of serving a real SPA.
   *
   * <p>Inspired from: <a href="https://stackoverflow.com/a/42998817/391221">Stack Overflow</a>
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/{spring:\\w+}").setViewName("forward:/");
    registry.addViewController("/**/{spring:\\w+}").setViewName("forward:/");
    registry
        .addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}")
        .setViewName("forward:/");
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    // index.html contains references to CSS & JS files. If we allow caching for index.html,
    // we risk of running into the issue that index.html is cached but the referenced js files are
    // no longer cached or available and the app fails to load. With caching disabled for
    // index.html, we make sure that we always get the proper resources.
    registry
        .addResourceHandler("/index.html")
        .addResourceLocations("classpath:/static/index.html")
        .setCacheControl(CacheControl.noCache());
  }
}
