package org.example.cliente.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cliente.entities.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URI;

public class UserRepository {

    private static final String BASE_URL = "http://localhost:8080/usuarios";
    private final ObjectMapper mapper = new ObjectMapper();

    public User validarCredenciais(String email, String senha) {
        try {

            String emailEnc = URLEncoder.encode(email, "UTF-8");
            String senhaEnc = URLEncoder.encode(senha, "UTF-8");

            String requestUrl = BASE_URL + "?email=" + emailEnc + "&senha=" + senhaEnc;

            URL url = URI.create(requestUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(4000);
            con.setReadTimeout(4000);

            int status = con.getResponseCode();

            if (status != 200) {
                System.out.println("Erro na requisição: " + status);
                return null;
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
            String json = response.toString();

            System.out.println("Resposta recebida: " + json);
            
            return mapper.readValue(json, User.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
