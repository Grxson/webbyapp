package com.scraper.interfaces.rest;

import com.scraper.application.port.in.JobService;
import com.scraper.interfaces.dto.job.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(jobService.getAllJobs(null, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id, null));
    }

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody CreateJobRequest request) {
        return ResponseEntity.ok(jobService.createJob(request, null));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelJob(@PathVariable Long id) {
        jobService.cancelJob(id, null);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/logs")
    public ResponseEntity<List<JobLogDto>> getJobLogs(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobLogs(id, null));
    }
}