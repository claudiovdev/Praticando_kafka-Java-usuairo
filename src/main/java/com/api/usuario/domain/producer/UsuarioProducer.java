package com.api.usuario.domain.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UsuarioProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void enviarMensagem(String payload, String topico){
        try {
            log.info("Enviando mensagem para o topico: {} com o payload: {}", topico, payload);
            kafkaTemplate.send(topico,payload);
        }catch (Exception exception){
            log.error("Erro ao enviar mensagem para o topico: {}", topico);
        }
    }
}
