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

    public void criarConta(String nome, String sobrenome, String email, 
                           String senha, String profissao, String nascimento) {
        try {
            // Criando objeto JSON para enviar
            Map<String, String> dados = new HashMap<>();
            dados.put("nome", nome);
            dados.put("sobrenome", sobrenome);
            dados.put("email", email);
            dados.put("senha", senha);
            dados.put("profissao", profissao);
            dados.put("nascimento", nascimento);

            String json = mapper.writeValueAsString(dados);

            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Envia JSON no corpo do POST
            OutputStream os = con.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            int status = con.getResponseCode();

            if (status == 201) {
                System.out.println("Conta criada com sucesso!");
            } else {
                System.out.println("Erro ao criar conta. Código: " + status);
            }

            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
