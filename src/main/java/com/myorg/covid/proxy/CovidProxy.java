package com.myorg.covid.proxy;

import com.myorg.covid.model.Covid;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "covidReport", path = "/api/cdc" ,url = "https://covidtracking.com")
public interface CovidProxy {

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "/daily")
  List<Covid> getReport();

}
