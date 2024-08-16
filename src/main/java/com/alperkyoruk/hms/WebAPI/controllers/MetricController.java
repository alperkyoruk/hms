package com.alperkyoruk.hms.WebAPI.controllers;

import com.alperkyoruk.hms.business.abstracts.MetricService;
import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.entities.DTOs.Metrics.GetMetricsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {
    private MetricService metricService;

    public MetricController(MetricService metricService){
        this.metricService = metricService;
    }

    @GetMapping("/getMetrics")
    public DataResult<GetMetricsDto> getMetrics(){
        return metricService.getMetrics();
    }
}
