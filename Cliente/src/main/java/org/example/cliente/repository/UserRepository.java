package org.example.cliente.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.example.cliente.entities.User;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {

    private static final String SERVER_URL = "http://localhost:8080/usuarios";

    private final ObjectMapper objectMapper = new ObjectMapper(); // para converter JSON em objeto

    public User validarCredenciais(String email, String senha) {
        try {
            // Monta a URL com parâmetros
            String urlComParametros = String.format("%s?email=%s&senha=%s",
                    SERVER_URL,
                    encode(email),
                    encode(senha));

            // Cria a URL
            URL url = URI.create(urlComParametros).toURL();

            // Abre a conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int status = connection.getResponseCode();

            if (status == 200) {
                // Lê o corpo da resposta
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Converte o JSON recebido em um objeto User
                    User user = objectMapper.readValue(response.toString(), User.class);
                    System.out.println("Usuário autenticado: " + user);
                    return user;
                }
            } else {
                System.out.println("Falha na autenticação. Código: " + status);
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String encode(String value) {
        try {
            return java.net.URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            return value;
        }
    }
}