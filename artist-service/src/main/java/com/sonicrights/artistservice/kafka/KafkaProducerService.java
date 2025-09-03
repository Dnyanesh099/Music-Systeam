package com.sonicrights.artistservice.kafka;

import com.sonicrights.artistservice.dto.ArtistEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, ArtistEvent> kafkaTemplate;

    private final String TOPIC = "artist-events";

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, ArtistEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendArtistCreatedEvent(ArtistEvent event) {
        kafkaTemplate.send(TOPIC, event.getArtistId().toString(), event);
    }
}
