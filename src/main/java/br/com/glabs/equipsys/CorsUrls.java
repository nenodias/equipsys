package br.com.glabs.equipsys;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CorsUrls {

    @Value("cors.urls")
    private String urls;

}
