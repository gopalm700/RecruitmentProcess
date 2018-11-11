package com.heavenhr.service;

import java.util.List;

import com.heavenhr.dto.ApplicationStatus;
import com.heavenhr.entity.Application;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;

public interface ApplicationService {

  Application createApplication(Application application)
      throws ConstraintsViolationException, DataNotFoundException;

  Application updateApplicationStatus(Long applicationId, ApplicationStatus applicationStatus)
      throws DataNotFoundException, ConstraintsViolationException;

  Application findById(Long applicationId) throws DataNotFoundException;

  List<Application> findAllByOfferId(final Long offerId);
}
