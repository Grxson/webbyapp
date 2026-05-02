package com.scraper.application.port.in;

import com.scraper.domain.ScrapedData;
import com.scraper.interfaces.dto.data.*;
import java.util.List;

public interface DataService {
    List<ScrapedData> getAllData(Long userId, Long scraperId, int page, int size);
    ScrapedData getDataById(Long id, Long userId);
    void deleteData(Long userId, Long scraperId, List<Long> dataIds);
}