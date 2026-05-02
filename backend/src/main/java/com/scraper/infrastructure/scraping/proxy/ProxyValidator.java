package com.scraper.infrastructure.scraping.proxy;

import com.scraper.domain.ProxyConfig;
import com.scraper.domain.enums.ProxyStatus;
import com.scraper.infrastructure.persistence.ProxyConfigRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ProxyValidator {

    private final ProxyConfigRepository proxyConfigRepository;

    public ProxyValidator(ProxyConfigRepository proxyConfigRepository) {
        this.proxyConfigRepository = proxyConfigRepository;
    }

    public boolean validate(ProxyConfig proxy) {
        try {
            long startTime = System.currentTimeMillis();

            // Simple connection test
            java.net.URL url = new java.net.URL("https://httpbin.org/ip");
            java.net.Proxy p = new java.net.Proxy(
                java.net.Proxy.Type.HTTP,
                new java.net.InetSocketAddress(proxy.getHost(), proxy.getPort())
            );
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection(p);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.getResponseCode();

            long responseTime = System.currentTimeMillis() - startTime;

            proxy.setStatus(ProxyStatus.ACTIVE);
            proxy.setResponseTimeMs((int) responseTime);
            proxy.setLastChecked(LocalDateTime.now());
            proxyConfigRepository.save(proxy);

            return true;
        } catch (Exception e) {
            proxy.setStatus(ProxyStatus.FAILED);
            proxy.setLastChecked(LocalDateTime.now());
            proxyConfigRepository.save(proxy);
            return false;
        }
    }
}