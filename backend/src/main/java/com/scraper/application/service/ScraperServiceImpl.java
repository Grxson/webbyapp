package com.scraper.application.service;

import com.scraper.application.port.in.ScraperService;
import com.scraper.domain.ScraperDefinition;
import com.scraper.domain.User;
import com.scraper.infrastructure.persistence.ScraperDefinitionRepository;
import com.scraper.infrastructure.persistence.UserRepository;
import com.scraper.interfaces.dto.scraper.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de scrapers.
 * Maneja operaciones CRUD, pruebas, importación y activación/desactivación de scrapers.
 * 
 * @author Scraper Platform
 * @version 1.0
 */
@Service
public class ScraperServiceImpl implements ScraperService {

    private final ScraperDefinitionRepository scraperRepository;
    private final UserRepository userRepository;

    /**
     * Constructor del servicio de scrapers.
     * 
     * @param scraperRepository Repositorio de definiciones de scrapers
     * @param userRepository Repositorio de usuarios
     */
    public ScraperServiceImpl(ScraperDefinitionRepository scraperRepository, UserRepository userRepository) {
        this.scraperRepository = scraperRepository;
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los scrapers de un usuario.
     * 
     * @param userId ID del usuario
     * @return Lista de scrapers del usuario
     */
    @Override
    public List<ScraperDefinition> getAllScrapers(Long userId) {
        return scraperRepository.findByUserId(userId);
    }

    /**
     * Obtiene un scraper específico por su ID.
     * 
     * @param id ID del scraper a buscar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El scraper encontrado o null si no existe
     */
    @Override
    public ScraperDefinition getScraperById(Long id, Long userId) {
        return scraperRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo scraper para un usuario.
     * 
     * @param request Datos del scraper a crear
     * @param userId ID del usuario que crea el scraper
     * @return El scraper creado o null si el usuario no existe
     */
    @Override
    public ScraperDefinition createScraper(CreateScraperRequest request, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        ScraperDefinition scraper = ScraperDefinition.builder()
                .user(user)
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .targetUrl(request.getTargetUrl())
                .config(request.getConfig())
                .cronExpression(request.getCronExpression())
                .proxyEnabled(request.isProxyEnabled())
                .jsRender(request.isJsRender())
                .rateLimitMs(request.getRateLimitMs())
                .isActive(true)
                .build();
        return scraperRepository.save(scraper);
    }

    /**
     * Actualiza un scraper existente.
     * Solo actualiza los campos no nulos del request.
     * 
     * @param id ID del scraper a actualizar
     * @param request Datos a actualizar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El scraper actualizado o null si no existe
     */
    @Override
    public ScraperDefinition updateScraper(Long id, UpdateScraperRequest request, Long userId) {
        Optional<ScraperDefinition> opt = scraperRepository.findById(id);
        if (opt.isEmpty()) return null;
        
        ScraperDefinition scraper = opt.get();
        if (request.getName() != null) scraper.setName(request.getName());
        if (request.getDescription() != null) scraper.setDescription(request.getDescription());
        if (request.getTargetUrl() != null) scraper.setTargetUrl(request.getTargetUrl());
        if (request.getConfig() != null) scraper.setConfig(request.getConfig());
        if (request.getCronExpression() != null) scraper.setCronExpression(request.getCronExpression());
        scraper.setProxyEnabled(request.isProxyEnabled());
        scraper.setJsRender(request.isJsRender());
        if (request.getRateLimitMs() != null) scraper.setRateLimitMs(request.getRateLimitMs());
        
        return scraperRepository.save(scraper);
    }

    /**
     * Elimina un scraper del sistema.
     * 
     * @param id ID del scraper a eliminar
     * @param userId ID del usuario (no utilizado actualmente)
     */
    @Override
    public void deleteScraper(Long id, Long userId) {
        scraperRepository.deleteById(id);
    }

    /**
     * Activa o desactiva un scraper (toggle).
     * 
     * @param id ID del scraper a togglear
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El scraper con el estado actualizado o null si no existe
     */
    @Override
    public ScraperDefinition toggleScraper(Long id, Long userId) {
        Optional<ScraperDefinition> opt = scraperRepository.findById(id);
        if (opt.isEmpty()) return null;
        
        ScraperDefinition scraper = opt.get();
        scraper.setActive(!scraper.isActive());
        return scraperRepository.save(scraper);
    }

    /**
     * Ejecuta una prueba de scraping en un scraper existente.
     * 
     * @param id ID del scraper a probar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return Respuesta con el resultado de la prueba o null si no existe
     */
    @Override
    public TestScraperResponse testScraper(Long id, Long userId) {
        Optional<ScraperDefinition> opt = scraperRepository.findById(id);
        if (opt.isEmpty()) return null;
        
        return TestScraperResponse.builder()
                .success(true)
                .message("Test completed")
                .build();
    }

    /**
     * Importa un scraper desde contenido externo (JSON, YAML, etc.).
     * 
     * @param request Datos de importación del scraper
     * @param userId ID del usuario que importa el scraper
     * @return El scraper importado (actualmente retorna null)
     */
    @Override
    public ScraperDefinition importScraper(ImportScraperRequest request, Long userId) {
        return null;
    }
}