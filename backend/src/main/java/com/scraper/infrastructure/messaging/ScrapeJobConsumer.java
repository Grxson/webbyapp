package com.scraper.infrastructure.messaging;

import com.scraper.config.RabbitMQConfig;
import com.scraper.infrastructure.persistence.ScrapeJobRepository;
import com.scraper.domain.ScrapeJob;
import com.scraper.domain.enums.JobStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ScrapeJobConsumer {

    private final ScrapeJobRepository jobRepository;

    public ScrapeJobConsumer(ScrapeJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.SCRAPE_QUEUE)
    public void processJob(Long jobId) {
        ScrapeJob job = jobRepository.findById(jobId).orElse(null);
        if (job == null) {
            return;
        }

        job.setStatus(JobStatus.RUNNING);
        job.setStartedAt(LocalDateTime.now());
        jobRepository.save(job);

        // TODO: Implement actual scraping logic here
        // This would call the scraper engine and process the job
    }
}