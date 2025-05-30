package com.yui.chat_in.service;

import com.yui.chat_in.service.exceptions.GeminiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${API_KEY}")
    private String API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generate(String prompt) {
        String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;


        Map<String, Object> textPart = Map.of("text", prompt);
        Map<String, Object> parts = Map.of("parts", List.of(textPart));
        Map<String, Object> requestBody = Map.of("contents", List.of(parts));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try{
            ResponseEntity<Map> response = restTemplate.exchange(
                    URL,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                List<Map<String, Object>> partsList = (List<Map<String, Object>>) content.get("parts");
                return (String) partsList.get(0).get("text");
            }

            return "Sem resposta.";

        }catch (HttpServerErrorException e){
            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                return "O modelo está temporariamente indisponível. Tente novamente mais tarde.";
            }
            throw new GeminiException("O recurso está indisponível no momento");
        }



    }
}
