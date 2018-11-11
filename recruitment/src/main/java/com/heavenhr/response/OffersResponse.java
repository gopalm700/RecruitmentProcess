package com.heavenhr.response;

import java.util.ArrayList;
import java.util.List;

import com.heavenhr.dto.OfferDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OffersResponse {

  private List<OfferDto> offers = new ArrayList<>();
}
