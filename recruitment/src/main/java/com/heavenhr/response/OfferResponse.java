package com.heavenhr.response;

import com.heavenhr.dto.OfferDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {

  private OfferDto offer;
}
