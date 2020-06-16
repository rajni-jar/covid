package com.myorg.covid.helper;

import com.myorg.covid.model.AverageMonthlyReport;
import com.myorg.covid.model.Covid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CovidHelper {

  public static List<AverageMonthlyReport> getReport(List<Covid> covidReport) {
    List<AverageMonthlyReport> monthlyReports = new ArrayList<>();
    Map<Integer, List<Covid>> months =
        covidReport
            .parallelStream()
            .map(
                covid -> {
                  String[] dateStrings = covid.getDateCollected().split("/");
                  LocalDate localDate =
                      LocalDate.of(
                          2020, Integer.valueOf(dateStrings[0]), Integer.valueOf(dateStrings[1]));
                  covid.setDateTime(localDate.atStartOfDay());
                  return covid;
                })
            .collect(Collectors.groupingBy(covid -> covid.getDateTime().getMonthValue()));
    months.forEach(
        (month, covids) -> {
          double avgCdcAndPublicHealth =
              covids
                  .parallelStream()
                  .mapToLong(
                      value -> value.getDailyTotal())
                  .sum();
          monthlyReports.add(
              AverageMonthlyReport.builder()
                  .month(month.toString())
                  .average(avgCdcAndPublicHealth)
                  .build());
          if (month == 4 || month == 5) {
            monthlyReports.add(
                AverageMonthlyReport.builder()
                    .month(month.toString() + "-10X")
                    .average(avgCdcAndPublicHealth * 10)
                    .build());
          }
        });
    // Avererage -10X report
    return monthlyReports;
  }
}
