package org.example.cliente.repository;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class CadastroRepository {

    private static final String BASE_URL = "http://localhost:8080/usuarios";
    private final ObjectMapper mapper = new ObjectMapper();

    public void cadastrarUsuario(String nome, String gmail, String password) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            Map<String, String> usuarioData = new HashMap<>();
            usuarioData.put("nome", nome);
            usuarioData.put("gmail", gmail);
            usuarioData.put("password", password);

            String jsonInputString = mapper.writeValueAsString(usuarioData);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            if (status != 200 && status != 201) {
                System.out.println("Erro ao cadastrar usuário: " + status);
            }

            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
