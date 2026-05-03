package com.scraper.application.service;

import com.scraper.application.port.in.ExportService;
import com.scraper.domain.ExportTask;
import com.scraper.infrastructure.persistence.ExportTaskRepository;
import com.scraper.interfaces.dto.export.CreateExportRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    private final ExportTaskRepository exportTaskRepository;

    public ExportServiceImpl(ExportTaskRepository exportTaskRepository) {
        this.exportTaskRepository = exportTaskRepository;
    }

    @Override
    public List<ExportTask> getAllExports(Long userId) {
        return exportTaskRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public ExportTask getExportById(Long id, Long userId) {
        return exportTaskRepository.findById(id)
                .filter(task -> task.getUser().getId().equals(userId))
                .orElse(null);
    }

    @Override
    public ExportTask createExport(CreateExportRequest request, Long userId) {
        return null;
    }

    @Override
    public byte[] downloadExport(Long id, Long userId) {
        return new byte[0];
    }
}