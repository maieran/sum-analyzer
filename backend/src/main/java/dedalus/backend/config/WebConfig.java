package dedalus.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.common.lang.NonNull;

/**
 * Ermöglicht HTTP-Anfragen zwischen Angular-Frontend und Spring-Boot-Backend
 */
@Configuration
public class WebConfig {



    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        //.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
                        .allowedMethods("GET", "POST");
			}
		};
	}

}