package ca.yorku.checkmate;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private static final String ORIGIN = "http://localhost:5173";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("api/**")
                .allowedOrigins(getOrigin())
                .allowCredentials(true);
    }

    public String getOrigin() {
        String frontendEnv = System.getenv("HOST_FRONTED");
        return frontendEnv == null ? ORIGIN : frontendEnv;
    }
}