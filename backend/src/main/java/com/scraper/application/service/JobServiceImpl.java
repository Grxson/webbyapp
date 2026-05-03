package com.scraper.application.service;

import com.scraper.application.port.in.JobService;
import com.scraper.domain.ScraperDefinition;
import com.scraper.domain.ScrapeJob;
import com.scraper.domain.enums.JobStatus;
import com.scraper.infrastructure.persistence.ScraperDefinitionRepository;
import com.scraper.infrastructure.persistence.ScrapeJobRepository;
import com.scraper.interfaces.dto.job.CreateJobRequest;
import com.scraper.interfaces.dto.job.JobLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Implementación del servicio de gestión de trabajos de scraping.
 * Maneja el ciclo de vida de los jobs: creación, consulta, cancelación y logs.
 * 
 * @author Scraper Platform
 * @version 1.0
 */
@Service
public class JobServiceImpl implements JobService {

    private final ScrapeJobRepository jobRepository;
    private final ScraperDefinitionRepository scraperRepository;

    /**
     * Constructor del servicio de jobs.
     * 
     * @param jobRepository Repositorio de trabajos de scraping
     * @param scraperRepository Repositorio de definiciones de scrapers
     */
    public JobServiceImpl(ScrapeJobRepository jobRepository, ScraperDefinitionRepository scraperRepository) {
        this.jobRepository = jobRepository;
        this.scraperRepository = scraperRepository;
    }

    /**
     * Obtiene todos los trabajos con paginación.
     * 
     * @param userId ID del usuario (no utilizado actualmente)
     * @param page Número de página
     * @param size Tamaño de página
     * @return Lista de trabajos en la página solicitada
     */
    @Override
    public List<ScrapeJob> getAllJobs(Long userId, int page, int size) {
        Page<ScrapeJob> jobs = jobRepository.findAll(PageRequest.of(page, size));
        return jobs.getContent();
    }

    /**
     * Obtiene un trabajo específico por su ID.
     * 
     * @param id ID del trabajo a buscar
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El trabajo encontrado o null si no existe
     */
    @Override
    public ScrapeJob getJobById(Long id, Long userId) {
        return jobRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo trabajo de scraping.
     * Asocia el trabajo a un scraper existente y lo inicializa en estado PENDING.
     * 
     * @param request Datos del trabajo a crear
     * @param userId ID del usuario (no utilizado actualmente)
     * @return El trabajo creado o null si el scraper no existe
     */
    @Override
    public ScrapeJob createJob(CreateJobRequest request, Long userId) {
        ScraperDefinition scraper = scraperRepository.findById(request.getScraperId()).orElse(null);
        if (scraper == null) return null;

        ScrapeJob job = ScrapeJob.builder()
                .scraper(scraper)
                .status(JobStatus.PENDING)
                .itemsScraped(0)
                .build();
        return jobRepository.save(job);
    }

    /**
     * Cancela un trabajo de scraping.
     * Solo puede cancelar trabajos en estado PENDING o RUNNING.
     * 
     * @param id ID del trabajo a cancelar
     * @param userId ID del usuario (no utilizado actualmente)
     */
    @Override
    public void cancelJob(Long id, Long userId) {
        jobRepository.findById(id).ifPresent(job -> {
            if (job.getStatus() == JobStatus.PENDING || job.getStatus() == JobStatus.RUNNING) {
                job.setStatus(JobStatus.CANCELLED);
                job.setCompletedAt(LocalDateTime.now());
                jobRepository.save(job);
            }
        });
    }

    /**
     * Obtiene los logs de un trabajo específico.
     * 
     * @param jobId ID del trabajo
     * @param userId ID del usuario (no utilizado actualmente)
     * @return Lista de logs del trabajo (actualmente vacío)
     */
    @Override
    public List<JobLogDto> getJobLogs(Long jobId, Long userId) {
        return Collections.emptyList();
    }
}