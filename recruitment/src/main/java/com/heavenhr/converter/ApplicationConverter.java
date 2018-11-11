package com.heavenhr.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heavenhr.dto.ApplicationDto;
import com.heavenhr.entity.Application;
import com.heavenhr.entity.Offer;
import com.heavenhr.exception.DataNotFoundException;
import com.heavenhr.service.OfferService;

@Component
public class ApplicationConverter {

  @Autowired
  private OfferService offerService;

  @Autowired
  private OfferConverter offerConverter;

  public Application dtoToEntity(ApplicationDto applicationDto) {
    Offer offer = null;
    try {
      offer = applicationDto.getOffer() != null
          ? offerService.findById(applicationDto.getOffer().getId()) : null;
    } catch (DataNotFoundException ignored) {
    }

    return Application.builder().id(applicationDto.getId())
        .applicationStatus(applicationDto.getApplicationStatus())
        .candidateEmail(applicationDto.getCandidateEmail()).relatedOffer(offer)
        .resumeText(applicationDto.getResumeText()).build();
  }

  public ApplicationDto entityToDto(Application application) {
    return ApplicationDto.builder().id(application.getId())
        .offer(offerConverter.entityToDto(application.getRelatedOffer()))
        .candidateEmail(application.getCandidateEmail()).resumeText(application.getResumeText())
        .applicationStatus(application.getApplicationStatus()).build();
  }
}
