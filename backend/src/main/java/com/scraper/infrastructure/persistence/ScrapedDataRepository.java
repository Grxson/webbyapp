package com.scraper.infrastructure.persistence;

import com.scraper.domain.ScrapedData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapedDataRepository extends JpaRepository<ScrapedData, Long> {
    Page<ScrapedData> findByScraperUserId(Long userId, Pageable pageable);
    Page<ScrapedData> findByScraperId(Long scraperId, Pageable pageable);
    java.util.List<ScrapedData> findByScraperIdOrderByScrapedAtDesc(Long scraperId);
}