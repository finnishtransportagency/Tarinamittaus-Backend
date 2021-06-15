package fi.tarina.tarinamittaus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class TarinamittausApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TarinamittausApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(TarinamittausApplication.class);
    }

}
