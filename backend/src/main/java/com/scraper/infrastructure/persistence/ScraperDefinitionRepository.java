package com.scraper.infrastructure.persistence;

import com.scraper.domain.ScraperDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScraperDefinitionRepository extends JpaRepository<ScraperDefinition, Long> {
    List<ScraperDefinition> findByUserId(Long userId);
    List<ScraperDefinition> findByUserIdAndIsActive(Long userId, boolean isActive);
}