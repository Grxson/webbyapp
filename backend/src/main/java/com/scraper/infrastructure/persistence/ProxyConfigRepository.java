package com.scraper.infrastructure.persistence;

import com.scraper.domain.ProxyConfig;
import com.scraper.domain.enums.ProxyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProxyConfigRepository extends JpaRepository<ProxyConfig, Long> {
    List<ProxyConfig> findByStatus(ProxyStatus status);
    List<ProxyConfig> findByStatusOrderBySuccessCountDesc(ProxyStatus status);
}