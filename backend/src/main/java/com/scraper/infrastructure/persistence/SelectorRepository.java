package com.scraper.infrastructure.persistence;

import com.scraper.domain.Selector;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SelectorRepository extends JpaRepository<Selector, Long> {
    List<Selector> findByScraperId(Long scraperId);
    void deleteByScraperId(Long scraperId);
}