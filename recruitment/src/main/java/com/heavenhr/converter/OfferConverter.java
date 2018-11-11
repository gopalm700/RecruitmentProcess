package com.heavenhr.converter;

import org.springframework.stereotype.Component;

import com.heavenhr.dto.OfferDto;
import com.heavenhr.entity.Offer;

@Component
public class OfferConverter {

  public OfferDto entityToDto(Offer offer) {
    return OfferDto.builder().id(offer.getId()).jobTitle(offer.getJobTitle())
        .numberOfApplications(offer.getNumberOfApplications()).startDate(offer.getStartDate())
        .build();
  }
}
