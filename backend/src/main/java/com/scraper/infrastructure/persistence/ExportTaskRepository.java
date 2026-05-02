package com.scraper.infrastructure.persistence;

import com.scraper.domain.ExportTask;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExportTaskRepository extends JpaRepository<ExportTask, Long> {
    List<ExportTask> findByUserIdOrderByCreatedAtDesc(Long userId);
}