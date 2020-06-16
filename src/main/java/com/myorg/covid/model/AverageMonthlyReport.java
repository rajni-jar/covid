package com.myorg.covid.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class AverageMonthlyReport {

  private String month;
  private Double average;

}
