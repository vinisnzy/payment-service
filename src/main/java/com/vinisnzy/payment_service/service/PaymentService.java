package com.vinisnzy.payment_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "orders.created", groupId = "payment-service-group")
    public void consume(String orderId) {
        boolean success = Math.random() < 0.9; // Simulate a 90% success rate
        String topic = success ? "payments.success" : "payments.failure";
        kafkaTemplate.send(topic, orderId);
    }
}
