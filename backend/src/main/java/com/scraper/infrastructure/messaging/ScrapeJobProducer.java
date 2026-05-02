package com.scraper.infrastructure.messaging;

import com.scraper.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScrapeJobProducer {

    private final RabbitTemplate rabbitTemplate;

    public ScrapeJobProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJob(Long jobId) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.SCRAPE_EXCHANGE,
            "scrape.job.execute",
            jobId
        );
    }
}