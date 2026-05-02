package com.scraper.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String SCRAPE_QUEUE = "scrape-job-queue";
    public static final String SCRAPE_EXCHANGE = "scrape-exchange";
    public static final String SCRAPE_ROUTING_KEY = "scrape.job.#";

    @Bean
    public Queue scrapeQueue() {
        return new Queue(SCRAPE_QUEUE, true);
    }

    @Bean
    public TopicExchange scrapeExchange() {
        return new TopicExchange(SCRAPE_EXCHANGE);
    }

    @Bean
    public Binding scrapeBinding(Queue scrapeQueue, TopicExchange scrapeExchange) {
        return BindingBuilder.bind(scrapeQueue).to(scrapeExchange).with(SCRAPE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}