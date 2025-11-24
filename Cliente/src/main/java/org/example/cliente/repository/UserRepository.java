package org.example.cliente.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.example.cliente.entities.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URI;

public class UserRepository {

    private static final String BASE_URL = "http://localhost:8080/usuarios";
    private final ObjectMapper mapper;

    public UserRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

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

    public boolean cadastrarUsuario(User user) {
        try {
            URL url = URI.create(BASE_URL).toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);


            String jsonInputString = mapper.writeValueAsString(user);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            if (status != 200 && status != 201) {
                System.out.println("Erro ao cadastrar usuário: " + status);
                return false;
            }

            con.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User updateUser(Integer id, User user) {
        try {
            String reqString = BASE_URL + "/" + id;

            System.out.println("Requisição URL: " + reqString);
            URL url = URI.create(reqString).toURL();
            System.out.println("URL criada: " + url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);


            String jsonInputString = mapper.writeValueAsString(user);
            System.out.println("JSON Enviado: " + jsonInputString);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            if (status != 200 && status != 201) {
                System.out.println("Erro ao cadastrar usuário: " + status);
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

            

            con.disconnect();
            return mapper.readValue(json, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
