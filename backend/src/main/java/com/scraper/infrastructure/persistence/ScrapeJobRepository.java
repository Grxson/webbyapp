package com.scraper.infrastructure.persistence;

import com.scraper.domain.ScrapeJob;
import com.scraper.domain.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScrapeJobRepository extends JpaRepository<ScrapeJob, Long> {
    Page<ScrapeJob> findByScraperUserId(Long userId, Pageable pageable);
    List<ScrapeJob> findByScraperUserIdAndStatus(Long userId, JobStatus status);
    List<ScrapeJob> findByScraperId(Long scraperId);
}