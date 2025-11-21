package org.example.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.example.cliente")
public class SpringBootApp {

    private static ConfigurableApplicationContext context;

    public static void init() {
        if (context == null) {
            SpringApplication app = new SpringApplication(SpringBootApp.class);
            app.setWebApplicationType(WebApplicationType.NONE);
            context = app.run();
        }
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootApp.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        context = app.run(args);
    }

}
