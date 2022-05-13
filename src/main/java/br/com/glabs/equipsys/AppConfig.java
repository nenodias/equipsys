package br.com.glabs.equipsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private CorsUrls corsUrls;

    private static String[] methods = Stream.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.HEAD, HttpMethod.OPTIONS, HttpMethod.TRACE, HttpMethod.PATCH).map(HttpMethod::name).toArray(String[]::new);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] urls = corsUrls.getUrls().split(",");
        registry.addMapping("/*")
                .allowedOrigins(urls)
                .allowedMethods(methods)
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
