package com.api.usuario.domain.producer;

import com.api.usuario.domain.model.Evento;
import com.api.usuario.domain.utils.JsonUitls;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.Future;

@Slf4j
@Component
@AllArgsConstructor
public class UsuarioProducer {

    private final KafkaProducer<String,Object> kafkaProducer;
    private final JsonUitls jsonUitls;


    public void enviarMensagem(String topico, Evento evento){
        Headers headers = new RecordHeaders();
       headers.add("event-type","registro-usuario".getBytes());
       headers.add("request-id", UUID.randomUUID().toString().getBytes());

        var encodeKey = Base64.getEncoder().encodeToString(evento.getIdTransacao().getBytes());
        var encodeValue = jsonUitls.toJson(evento);

        ProducerRecord<String,Object> record = new ProducerRecord<>(topico,null,encodeKey,encodeValue, headers);

        try {
            Future<RecordMetadata> future = kafkaProducer.send(record);
            RecordMetadata recordMetadata = future.get();
            log.info("Mensagem enviada com sucesso para o topico: {}, na partição {}", recordMetadata.topic(),recordMetadata.partition());
        }catch (Exception ex){
            log.error("Erro ao enviar mensagem para o topico: {}",topico, ex);
        }
    }
}
