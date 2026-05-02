package com.scraper.infrastructure.scraping.proxy;

import com.scraper.domain.ProxyConfig;
import com.scraper.domain.enums.ProxyStatus;
import com.scraper.infrastructure.persistence.ProxyConfigRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProxyRotator {

    private final ProxyConfigRepository proxyConfigRepository;

    public ProxyRotator(ProxyConfigRepository proxyConfigRepository) {
        this.proxyConfigRepository = proxyConfigRepository;
    }

    public Optional<ProxyConfig> getNextProxy() {
        List<ProxyConfig> activeProxies = proxyConfigRepository
            .findByStatusOrderBySuccessCountDesc(ProxyStatus.ACTIVE);

        if (activeProxies.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(activeProxies.get(0));
    }

    public void recordSuccess(ProxyConfig proxy) {
        proxy.setSuccessCount(proxy.getSuccessCount() + 1);
        proxyConfigRepository.save(proxy);
    }

    public void recordFailure(ProxyConfig proxy) {
        proxy.setFailCount(proxy.getFailCount() + 1);
        if (proxy.getFailCount() > 10) {
            proxy.setStatus(ProxyStatus.FAILED);
        }
        proxyConfigRepository.save(proxy);
    }
}