package com.scraper.application.service;

import com.scraper.application.port.in.DataService;
import com.scraper.domain.ScrapedData;
import com.scraper.infrastructure.persistence.ScrapedDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de gestión de datos raspados.
 * Maneja la consulta y eliminación de datos almacenados del scraping.
 * 
 * @author Scraper Platform
 * @version 1.0
 */
@Service
public class DataServiceImpl implements DataService {

    private final ScrapedDataRepository scrapedDataRepository;

    /**
     * Constructor del servicio de datos.
     * 
     * @param scrapedDataRepository Repositorio de datos raspados
     */
    public DataServiceImpl(ScrapedDataRepository scrapedDataRepository) {
        this.scrapedDataRepository = scrapedDataRepository;
    }

    /**
     * Obtiene los datos raspados con soporte de paginación y filtrado por scraper.
     * 
     * @param userId ID del usuario para filtrar datos
     * @param scraperId ID del scraper para filtrar datos específicos (opcional)
     * @param page Número de página para paginación
     * @param size Tamaño de página
     * @return Lista de datos raspados
     */
    @Override
    public List<ScrapedData> getAllData(Long userId, Long scraperId, int page, int size) {
        if (scraperId != null) {
            return scrapedDataRepository.findByScraperIdOrderByScrapedAtDesc(scraperId);
        }
        return scrapedDataRepository.findAll();
    }

    /**
     * Obtiene un dato específico por su ID.
     * 
     * @param id ID del dato a buscar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El dato encontrado o null si no existe
     */
    @Override
    public ScrapedData getDataById(Long id, Long userId) {
        return scrapedDataRepository.findById(id).orElse(null);
    }

    /**
     * Elimina múltiples datos raspados.
     * 
     * @param userId ID del usuario (no utilizado actualmente)
     * @param scraperId ID del scraper (no utilizado actualmente)
     * @param dataIds Lista de IDs de datos a eliminar
     */
    @Override
    public void deleteData(Long userId, Long scraperId, List<Long> dataIds) {
        for (Long id : dataIds) {
            scrapedDataRepository.deleteById(id);
        }
    }
}