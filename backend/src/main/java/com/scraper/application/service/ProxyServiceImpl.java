package com.scraper.application.service;

import com.scraper.application.port.in.ProxyService;
import com.scraper.domain.ProxyConfig;
import com.scraper.domain.User;
import com.scraper.domain.enums.ProxyStatus;
import com.scraper.infrastructure.persistence.ProxyConfigRepository;
import com.scraper.infrastructure.persistence.UserRepository;
import com.scraper.interfaces.dto.proxy.CreateProxyRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de proxies.
 * Maneja operaciones CRUD y validación de proxies del sistema.
 * 
 * @author Scraper Platform
 * @version 1.0
 */
@Service
public class ProxyServiceImpl implements ProxyService {

    private final ProxyConfigRepository proxyConfigRepository;
    private final UserRepository userRepository;

    /**
     * Constructor del servicio de proxies.
     * 
     * @param proxyConfigRepository Repositorio de configuración de proxies
     * @param userRepository Repositorio de usuarios
     */
    public ProxyServiceImpl(ProxyConfigRepository proxyConfigRepository, UserRepository userRepository) {
        this.proxyConfigRepository = proxyConfigRepository;
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los proxies del sistema.
     * 
     * @param userId ID del usuario (no utilizado actualmente)
     * @return Lista de todos los proxies configurados
     */
    @Override
    public List<ProxyConfig> getAllProxies(Long userId) {
        return proxyConfigRepository.findAll();
    }

    /**
     * Obtiene un proxy específico por su ID.
     * 
     * @param id ID del proxy a buscar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El proxy encontrado o null si no existe
     */
    @Override
    public ProxyConfig getProxyById(Long id, Long userId) {
        return proxyConfigRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo proxy en el sistema.
     * 
     * @param request Datos del proxy a crear
     * @param userId ID del usuario que crea el proxy
     * @return El proxy creado y persistido
     */
    @Override
    public ProxyConfig createProxy(CreateProxyRequest request, Long userId) {
        ProxyConfig proxy = ProxyConfig.builder()
                .host(request.getHost())
                .port(request.getPort())
                .protocol(request.getProtocol() != null ? request.getProtocol() : "http")
                .username(request.getUsername())
                .password(request.getPassword())
                .status(ProxyStatus.TESTING)
                .successCount(0)
                .failCount(0)
                .build();
        return proxyConfigRepository.save(proxy);
    }

    /**
     * Elimina un proxy del sistema.
     * 
     * @param id ID del proxy a eliminar
     * @param userId ID del usuario (no utilizado actualmente)
     */
    @Override
    public void deleteProxy(Long id, Long userId) {
        proxyConfigRepository.deleteById(id);
    }

    /**
     * Valida todos los proxies del sistema.
     * Itera sobre todos los proxies y ejecuta la validación individual.
     * 
     * @param userId ID del usuario (no utilizado actualmente)
     */
    @Override
    public void validateAllProxies(Long userId) {
        List<ProxyConfig> proxies = proxyConfigRepository.findAll();
        for (ProxyConfig proxy : proxies) {
            validateProxy(proxy.getId(), userId);
        }
    }

    /**
     * Valida un proxy específico intentando conectar a través de él.
     * Actualiza el estado del proxy según el resultado de la conexión.
     * 
     * @param id ID del proxy a validar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El proxy con el estado actualizado o null si no existe
     */
    @Override
    public ProxyConfig validateProxy(Long id, Long userId) {
        Optional<ProxyConfig> optProxy = proxyConfigRepository.findById(id);
        if (optProxy.isEmpty()) return null;
        
        ProxyConfig proxy = optProxy.get();
        
        try {
            String url = String.format("%s://%s:%d", 
                proxy.getProtocol() != null ? proxy.getProtocol() : "http",
                proxy.getHost(), 
                proxy.getPort());
            
            long start = System.currentTimeMillis();
            java.net.Proxy.Type type = proxy.getProtocol() != null && proxy.getProtocol().equals("socks5") 
                ? java.net.Proxy.Type.SOCKS 
                : java.net.Proxy.Type.HTTP;
            java.net.Proxy javaProxy = new java.net.Proxy(type, new java.net.InetSocketAddress(proxy.getHost(), proxy.getPort()));
            
            java.net.URL urlObj = new java.net.URL("http://checkip.amazonaws.com");
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlObj.openConnection(javaProxy);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.getResponseCode();
            long responseTime = System.currentTimeMillis() - start;
            
            proxy.setStatus(ProxyStatus.ACTIVE);
            proxy.setSuccessCount(proxy.getSuccessCount() + 1);
            proxy.setResponseTimeMs((int) responseTime);
        } catch (Exception e) {
            proxy.setStatus(ProxyStatus.FAILED);
            proxy.setFailCount(proxy.getFailCount() + 1);
        }
        
        proxy.setLastChecked(java.time.LocalDateTime.now());
        return proxyConfigRepository.save(proxy);
    }
}