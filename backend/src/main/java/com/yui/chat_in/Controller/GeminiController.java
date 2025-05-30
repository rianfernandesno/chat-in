package com.yui.chat_in.Controller;

import com.yui.chat_in.models.dto.GeminiPrompt;
import com.yui.chat_in.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/answer")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping
    public ResponseEntity<Map<String, String>> toAsk(@RequestBody GeminiPrompt prompt) {
        String ask = geminiService.generate(prompt.getPrompt());
        return ResponseEntity.ok(Map.of("resposta", ask));
    }
}
