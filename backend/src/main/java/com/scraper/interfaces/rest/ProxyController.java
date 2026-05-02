package com.scraper.interfaces.rest;

import com.scraper.application.port.in.ProxyService;
import com.scraper.interfaces.dto.proxy.CreateProxyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proxies")
public class ProxyController {

    private final ProxyService proxyService;

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProxies() {
        return ResponseEntity.ok(proxyService.getAllProxies(null));
    }

    @PostMapping
    public ResponseEntity<?> createProxy(@RequestBody CreateProxyRequest request) {
        return ResponseEntity.ok(proxyService.createProxy(request, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProxy(@PathVariable Long id) {
        proxyService.deleteProxy(id, null);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validate-all")
    public ResponseEntity<Void> validateAllProxies() {
        proxyService.validateAllProxies(null);
        return ResponseEntity.accepted().build();
    }
}