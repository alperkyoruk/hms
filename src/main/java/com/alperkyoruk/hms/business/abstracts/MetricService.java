package com.alperkyoruk.hms.business.abstracts;

import com.alperkyoruk.hms.core.result.DataResult;
import com.alperkyoruk.hms.core.result.Result;
import com.alperkyoruk.hms.entities.DTOs.Metrics.GetMetricsDto;

public interface MetricService {
    DataResult<GetMetricsDto> getMetrics();
}
