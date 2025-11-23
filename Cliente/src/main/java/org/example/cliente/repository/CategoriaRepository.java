package org.example.cliente.repository;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

import org.example.cliente.entities.Categoria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CategoriaRepository {
    
    public static final String BASE_URL = "http://localhost:8080/categorias";
    private final ObjectMapper mapper;

    public CategoriaRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void salvarCategoria(Categoria categoria) {
        try {
            String requestUrl = BASE_URL;

            URL url = URI.create(requestUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            String jsonInputString = this.mapper.writeValueAsString(categoria);

            try (java.io.OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            System.out.println("DEBUG JSON Enviado: " + jsonInputString);

            int status = con.getResponseCode();
            if (status != 200 && status != 201) {
                System.out.println("Erro POST transação: " + status);
            }

            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteCategoria(int categoriaId) {
        try {
            String requestUrl = BASE_URL + "/" + categoriaId;

            URL url = URI.create(requestUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            int status = con.getResponseCode();
            if (status != 200 && status != 204) {
                System.out.println("Erro DELETE transação: " + status);
            }

            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
