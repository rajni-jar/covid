package com.myorg.covid.controller;

import com.myorg.covid.model.AverageMonthlyReport;
import com.myorg.covid.model.Covid;
import com.myorg.covid.proxy.ServiceProxy;
import com.myorg.covid.service.CovidService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value ="/covid",
    produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "/covid",
    tags = "covid")
public class CovidReportController {
  @Autowired
  private ServiceProxy serviceProxy;

  @Autowired
  private CovidService covidService;

  @GetMapping("/daily")
  @CrossOrigin
  public List<Covid> getReport() {
    return serviceProxy.getReport();

  }

  @GetMapping("/dailyAverage")
  @CrossOrigin
  public List<AverageMonthlyReport> getReportAverage() {
    return covidService.getMonthlyReport();
  }


}
