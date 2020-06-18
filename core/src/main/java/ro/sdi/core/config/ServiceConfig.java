package ro.sdi.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
@ComponentScan({"ro.sdi.core.validators", "ro.sdi.core.service"})
public class ServiceConfig
{
    @Bean
    DateTimeFormatter dateFormatter()
    {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }
}
