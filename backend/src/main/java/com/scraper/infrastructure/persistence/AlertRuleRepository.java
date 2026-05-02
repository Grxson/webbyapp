package com.scraper.infrastructure.persistence;

import com.scraper.domain.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRuleRepository extends JpaRepository<AlertRule, Long> {
    List<AlertRule> findByScraperIdAndEnabled(Long scraperId, boolean enabled);
}