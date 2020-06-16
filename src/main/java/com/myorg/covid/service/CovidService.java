package com.myorg.covid.service;

import com.myorg.covid.helper.CovidHelper;
import com.myorg.covid.model.AverageMonthlyReport;
import com.myorg.covid.proxy.ServiceProxy;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CovidService {

  @Autowired private ServiceProxy serviceProxy;

  private static final Calendar cal = Calendar.getInstance();

  public List<AverageMonthlyReport> getMonthlyReport() {

    return CovidHelper.getReport(serviceProxy
        .getReport());

  }
}
