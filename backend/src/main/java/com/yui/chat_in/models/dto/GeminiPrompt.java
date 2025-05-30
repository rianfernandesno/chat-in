package com.yui.chat_in.models.dto;

public class GeminiPrompt {

    private String prompt;

    public GeminiPrompt(){}

    public  GeminiPrompt(String prompt){
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }


}
