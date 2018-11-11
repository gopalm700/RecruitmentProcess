package com.heavenhr.dto;


import org.hibernate.validator.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

  private Long id;

  private OfferDto offer;

  @Email
  private String candidateEmail;

  private String resumeText;

  private ApplicationStatus applicationStatus;

  @Override
  public String toString() {
    return "ApplicationDto [id=" + id + ", offer=" + offer + ", candidateEmail=" + candidateEmail
        + ", resumeText=" + resumeText + ", applicationStatus=" + applicationStatus + "]";
  }



}
