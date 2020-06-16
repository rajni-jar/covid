package com.myorg.covid.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Data;


@Data
@JsonInclude(Include.NON_NULL)
public class Covid {

  private String dateCollected;
  private Long cdcLabs;
  private Long usPubHealthLabs;
  private Long dailyTotal;
  private Long lag;
  private LocalDateTime dateTime;

}
