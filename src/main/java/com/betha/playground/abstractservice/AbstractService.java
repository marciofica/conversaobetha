package com.betha.playground.abstractservice;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by marcio.figueiredo on 14/12/2015.
 */
public abstract class AbstractService {

    private static final String BASE_URL = "http://e-gov.betha.com.br/arrecadacao-conversao-services/rest/conversao";
    private static final String CONSULTA_URL = "/consultas/registros";
    private static final String TOKEN_BEARER = "Bearer <colocar seu token aqui>";

    public abstract String servico();

    public abstract String caminhoDoArquivo();

    public String consultar(String hash){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", TOKEN_BEARER);

        HttpEntity entity = new HttpEntity(headers);

        System.out.println("url: " + BASE_URL + CONSULTA_URL + "/" + hash);
        ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL + CONSULTA_URL + "/" + hash, HttpMethod.GET, entity, String.class);

        return responseEntity.getBody();
    }

    public String enviar() {
        String retorno = "";
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo()));
            StringBuilder conteudo = new StringBuilder();
            while (br.ready()) {
                conteudo.append(br.readLine());
            }
            br.close();
            System.out.println("Enviando arquivo: " + caminhoDoArquivo());
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            headers.set("Authorization", TOKEN_BEARER);

            HttpEntity<String> entity = new HttpEntity<String>(conteudo.toString(), headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(BASE_URL + servico(), HttpMethod.POST, entity, String.class);

            retorno = responseEntity.getBody();
        } catch (FileNotFoundException e) {
            Map<String, Object> erro = new LinkedHashMap<>();
            erro.put("data", "O arquivo informado não existe: " + e.getMessage());
            return gson.toJson(erro);
        } catch (IOException e) {
            Map<String, Object> erro = new LinkedHashMap<>();
            erro.put("data", "Problemas ao abrir o arquivo: " + e.getMessage());
            return gson.toJson(erro);
        }
        return retorno;
    }
}
