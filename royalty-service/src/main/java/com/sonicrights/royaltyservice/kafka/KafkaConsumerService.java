package com.sonicrights.royaltyservice.kafka;

import com.sonicrights.royaltyservice.dto.ArtistEvent;
import com.sonicrights.royaltyservice.entity.Royalty;
import com.sonicrights.royaltyservice.repository.RoyaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {

    private final RoyaltyRepository royaltyRepository;

    @Autowired
    public KafkaConsumerService(RoyaltyRepository royaltyRepository) {
        this.royaltyRepository = royaltyRepository;
    }

    @KafkaListener(topics = "artist-events", groupId = "royalty-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeArtistCreatedEvent(ArtistEvent event) {
        System.out.println("🎧 Received event from Kafka: " + event.getArtistId());

        Royalty royalty = new Royalty();
        royalty.setArtistId(event.getArtistId());
        royalty.setAmount(BigDecimal.ZERO);
        royalty.setEventType("CREATED");
        royalty.setCreatedAt(LocalDateTime.now());

        royaltyRepository.save(royalty);
        System.out.println("Royalty record created for artist: " + event.getName());
    }
}
