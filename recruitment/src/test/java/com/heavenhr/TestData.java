package com.heavenhr;

import java.util.Calendar;

import com.heavenhr.dto.ApplicationDto;
import com.heavenhr.dto.ApplicationStatus;
import com.heavenhr.dto.OfferDto;
import com.heavenhr.entity.Application;
import com.heavenhr.entity.Offer;

public class TestData {

  public static final Long OFFER_ID = 1L;
  public static final Long APPLICATION_ID = 11L;

  public static Offer getOffer(final Long id, final String jobTitle) {
    return Offer.builder().id(id).jobTitle(jobTitle).numberOfApplications(0)
        .startDate(Calendar.getInstance()).build();
  }

  public static Application getApplication(final Long id, final Long offerId) {
    return getApplication(id, getOffer(offerId, "jobTitle"));
  }

  public static Application getApplication(final Long id, final Offer offer) {
    return Application.builder().id(id).resumeText("resume").candidateEmail("test@example.com")
        .applicationStatus(ApplicationStatus.APPLIED).relatedOffer(offer).build();
  }

  public static ApplicationDto getApplicationDto(final Long id, final Long offerId) {
    return ApplicationDto.builder().id(id).resumeText("resume").candidateEmail("test@example.com")
        .applicationStatus(ApplicationStatus.APPLIED).offer(OfferDto.builder().id(offerId).build())
        .build();
  }

  public static String createURLWithPort(String uri, int port) {
    return "http://localhost:" + port + uri;
  }
}
