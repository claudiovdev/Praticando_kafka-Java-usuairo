package com.api.usuario.domain.utils;

import com.api.usuario.domain.entity.Evento;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonUitls {

    private final ObjectMapper objectMapper;

    public String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            return "";
        }
    }
}
