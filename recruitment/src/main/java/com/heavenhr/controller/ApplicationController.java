package com.heavenhr.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.converter.ApplicationConverter;
import com.heavenhr.dto.ApplicationDto;
import com.heavenhr.dto.ApplicationStatus;
import com.heavenhr.entity.Application;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;
import com.heavenhr.response.ApplicationResponse;
import com.heavenhr.response.ApplicationsResponse;
import com.heavenhr.service.ApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Applications")
public class ApplicationController {
  private static Logger log = LoggerFactory.getLogger(ApplicationController.class);


  @Autowired
  private ApplicationService applicationService;

  @Autowired
  private ApplicationConverter applicationConverter;


  @PostMapping("/v1/application")
  @ResponseBody
  @ResponseStatus(CREATED)
  @ApiOperation(value = "Create application")
  public ApplicationResponse createApplication(@Valid @RequestBody ApplicationDto applicationDto)
      throws ConstraintsViolationException, DataNotFoundException {

    Application application = applicationConverter.dtoToEntity(applicationDto);
    application = applicationService.createApplication(application);
    log.info("Application created with id - {}", application.getId());
    return new ApplicationResponse(applicationConverter.entityToDto(application));
  }

  @GetMapping("/v1/application/{id}")
  @ResponseBody
  @ApiOperation(value = "Find application by id")
  public ApplicationResponse findApplicationById(@Valid @PathVariable Long id)
      throws DataNotFoundException {
    return new ApplicationResponse(
        applicationConverter.entityToDto(applicationService.findById(id)));
  }


  @PutMapping("/v1/application/{id}")
  @ResponseBody
  @ApiOperation(value = "Update application status")
  public ApplicationResponse updateApplicationStatus(@Valid @PathVariable long id,
      @RequestBody String status) throws ConstraintsViolationException, DataNotFoundException {
    ApplicationStatus applicationStatus = null;
    try {
      applicationStatus = ApplicationStatus.valueOf(status);
    } catch (Exception e) {
      throw new DataNotFoundException("Corrupted data");
    }
    return new ApplicationResponse(applicationConverter
        .entityToDto(applicationService.updateApplicationStatus(id, applicationStatus)));
  }

  @GetMapping("/v1/application/offer/{id}")
  @ResponseBody
  @ApiOperation(value = "Find applications by offer id")
  public ApplicationsResponse findApplicationsByOfferId(@Valid @PathVariable Long id) {
    return new ApplicationsResponse(applicationService.findAllByOfferId(id).stream()
        .map(applicationConverter::entityToDto).collect(toList()));
  }

}
