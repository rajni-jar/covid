package com.myorg.covid.controller;

import com.myorg.covid.model.AverageMonthlyReport;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CovidReportControllerTest {

  @Autowired private WebTestClient webTestClient;

  @Autowired private CovidReportController covidReportController;

  @Test
  public void testRetrieveInventoryLists() {

    webTestClient
        .get()
        .uri("/covid/dailyAverage")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(AverageMonthlyReport[].class)
        .consumeWith(
            responseEntityEntityExchangeResult -> {
              List<AverageMonthlyReport> averageMonthlyReports =
                  Arrays.asList(responseEntityEntityExchangeResult.getResponseBody());
              Assertions.assertNotNull(averageMonthlyReports);
              averageMonthlyReports.stream()
                  .findFirst()
                  .ifPresent(
                      averageMonthlyReport -> {
                        switch (averageMonthlyReport.getMonth()) {
                          case "1":
                            Assertions.assertEquals(856.0, averageMonthlyReport.getAverage());
                            break;
                          case "2":
                            Assertions.assertEquals(3190.0, averageMonthlyReport.getAverage());
                            break;
                          case "3":
                            Assertions.assertEquals(187393.0, averageMonthlyReport.getAverage());
                            break;
                          case "4":
                            Assertions.assertEquals(51137.0, averageMonthlyReport.getAverage());
                            break;
                        }
                      });
            });
  }
}
