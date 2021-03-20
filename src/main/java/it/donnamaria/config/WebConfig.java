package it.donnamaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.1.147:3000", "http://localhost:3000", "http://donna-maria.sytes.net")
                .allowedHeaders("Access-Control-Allow-Origin", "*")
                .allowedMethods("PUT", "DELETE", "POST", "GET")
                .allowCredentials(false)
                .maxAge(3600);
    }
}