package com.heavenhr.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.converter.OfferConverter;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;
import com.heavenhr.response.ApplicationCountResponse;
import com.heavenhr.response.OfferResponse;
import com.heavenhr.response.OffersResponse;
import com.heavenhr.service.OfferService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Offers")
public class OfferController {

  @Autowired
  private OfferService offerService;

  @Autowired
  private OfferConverter offerConverter;

  @PostMapping("/v1/offer")
  @ResponseBody
  @ApiOperation(value = "Create offer")
  @ResponseStatus(CREATED)
  public OfferResponse createOffer(@Valid @RequestBody String jobTitle)
      throws ConstraintsViolationException {
    return new OfferResponse(offerConverter.entityToDto(offerService.createOffer(jobTitle)));
  }

  @GetMapping(path = "/v1/offer/{id}")
  @ResponseBody
  @ApiOperation(value = "Find offer by id")
  public OfferResponse findOfferById(@Valid @PathVariable Long id) throws DataNotFoundException {
    return new OfferResponse(offerConverter.entityToDto(offerService.findById(id)));
  }


  @GetMapping(path = "/v1/offer")
  @ResponseBody
  @ApiOperation(value = "Find all offers")
  public OffersResponse findOffers() {
    return new OffersResponse(offerService.findAll().stream().map(offerConverter::entityToDto)
        .collect(Collectors.toList()));
  }

  @GetMapping("/v1/offer/{id}/applicationcount")
  @ResponseBody
  @ApiOperation(value = "Track the number of applications")
  public ApplicationCountResponse trackNumberOfApplicationsById(@Valid @PathVariable Long id)
      throws DataNotFoundException {
    return new ApplicationCountResponse(offerService.findById(id).getNumberOfApplications());
  }
}
