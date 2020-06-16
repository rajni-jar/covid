package com.myorg.covid.proxy;

import com.myorg.covid.model.Covid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProxy {

  @Autowired
  private CovidProxy covidProxy;

  public List<Covid> getReport() {
    return covidProxy.getReport();
  }

}
