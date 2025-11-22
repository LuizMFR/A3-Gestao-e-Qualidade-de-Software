package org.example.cliente.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.example.cliente.entities.Categoria;
import org.example.cliente.entities.Transacao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;


public class HomeRepository {

    private static final String BASE_URL = "http://localhost:8080/transacoes";
    public final ObjectMapper mapper;

    public HomeRepository() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public List<Transacao> findByUserId(Integer usuarioId) {
        try {
            String requestUrl = BASE_URL + "/usuario/" + usuarioId;

            URL url = URI.create(requestUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Erro GET transações: " + status);
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) sb.append(line);

            br.close();
            con.disconnect();

            Transacao[] array = this.mapper.readValue(sb.toString(), Transacao[].class);
            return Arrays.asList(array);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categoria> findCategoriasByUserId(Integer usuarioId) {
        try {
            String requestUrl = "http://localhost:8080/categorias/" + usuarioId;

            URL url = URI.create(requestUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Erro GET categorias: " + status);
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) sb.append(line);

            br.close();
            con.disconnect();

            Categoria[] array = this.mapper.readValue(sb.toString(), Categoria[].class);
            return Arrays.asList(array);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
