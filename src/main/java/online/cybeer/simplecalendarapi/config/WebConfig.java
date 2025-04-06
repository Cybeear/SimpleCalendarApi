package online.cybeer.simplecalendarapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${ui.application.url}")
    private String uiApplicationUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(uiApplicationUrl)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
