package com.scraper.application.port.in;

import com.scraper.domain.ProxyConfig;
import com.scraper.interfaces.dto.proxy.*;
import java.util.List;

public interface ProxyService {
    List<ProxyConfig> getAllProxies(Long userId);
    ProxyConfig getProxyById(Long id, Long userId);
    ProxyConfig createProxy(CreateProxyRequest request, Long userId);
    void deleteProxy(Long id, Long userId);
    void validateAllProxies(Long userId);
    ProxyConfig validateProxy(Long id, Long userId);
}