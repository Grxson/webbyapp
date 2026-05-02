package com.scraper.application.port.in;

import com.scraper.domain.ScrapeJob;
import com.scraper.interfaces.dto.job.*;
import java.util.List;

public interface JobService {
    List<ScrapeJob> getAllJobs(Long userId, int page, int size);
    ScrapeJob getJobById(Long id, Long userId);
    ScrapeJob createJob(CreateJobRequest request, Long userId);
    void cancelJob(Long id, Long userId);
    List<JobLogDto> getJobLogs(Long jobId, Long userId);
}