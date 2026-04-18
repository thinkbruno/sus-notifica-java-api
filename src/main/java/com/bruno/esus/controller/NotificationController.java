package com.bruno.esus.controller;

import com.bruno.esus.model.EsusRecord;
import com.bruno.esus.service.EsusDataService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/opendata")
public class NotificationController {

    private final EsusDataService esusDataService;

    public NotificationController(EsusDataService esusDataService) {
        this.esusDataService = esusDataService;
    }

    @GetMapping("/fetch")
    public List<EsusRecord> fetch(@RequestParam(defaultValue = "10") int limit) {
        return esusDataService.getProcessedRecords(limit);
    }
}