package org.example.cliente.repository;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

        private static final String SERVER_URL = "http://localhost:8080/usuarios";

    public boolean validarCredenciais(String email, String senha) {
        try {
            // Cria o JSON da requisição
            String json = String.format("{\"email\":\"%s\", \"senha\":\"%s\"}", email, senha);

            // Cria a URL de forma segura (sem usar o construtor de URL diretamente)
            URL url = URI.create(SERVER_URL).toURL();

            // Abre a conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Envia o JSON
            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes("utf-8"));
            }

            // Lê o código de resposta (200 = OK)
            int status = connection.getResponseCode();
            return status == 200;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
