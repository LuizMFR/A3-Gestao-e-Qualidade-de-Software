package org.example.cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.springframework.context.ConfigurableApplicationContext;

public class HelloApplication extends Application {
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        SpringBootApp.init();
        springContext = SpringBootApp.getContext();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws Exception {
        if (springContext != null) {
            springContext.close(); // Finaliza o Spring Boot
        }
        super.stop(); // chama o método original do JavaFX
    }

    public static void main(String[] args) {
        launch();
    }
}
