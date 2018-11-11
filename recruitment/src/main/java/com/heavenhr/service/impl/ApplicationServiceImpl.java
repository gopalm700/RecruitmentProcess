package com.heavenhr.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heavenhr.dto.ApplicationStatus;
import com.heavenhr.dto.NotifyObject;
import com.heavenhr.entity.Application;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;
import com.heavenhr.factory.NotificationFactory;
import com.heavenhr.repository.ApplicationRepo;
import com.heavenhr.service.ApplicationService;
import com.heavenhr.service.OfferService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

  private static Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

  @Autowired
  private ApplicationRepo applicationRepo;

  @Autowired
  private OfferService offerService;

  @Qualifier("notificationFactory")
  @Autowired
  private NotificationFactory notificationFactory;

  @Override
  public Application createApplication(Application application)
      throws ConstraintsViolationException, DataNotFoundException {
    validateApplication(application);
    application.setApplicationStatus(ApplicationStatus.APPLIED);
    Application app = save(application);
    offerService.increaseNumberOfApplications(app.getRelatedOffer());
    return app;
  }

  @Override
  public Application updateApplicationStatus(final Long id,
      final ApplicationStatus applicationStatus)
      throws DataNotFoundException, ConstraintsViolationException {
    Application application = findById(id);
    if (applicationStatus.getStageOrder() >= application.getApplicationStatus().getStageOrder()) {
      application.setApplicationStatus(applicationStatus);
      application = save(application);
      notificationFactory.get(applicationStatus.name())
          .notifyEvent(new NotifyObject(application, applicationStatus));
      return application;
    } else {
      throw new ConstraintsViolationException("Cannot change application status to lower stage");
    }
  }

  @Override
  public Application findById(final Long id) throws DataNotFoundException {
    return checkApplication(applicationRepo.findOne(id));
  }

  @Override
  public List<Application> findAllByOfferId(final Long id) {
    return applicationRepo.findByOffer(id);
  }

  private Application checkApplication(Application application) throws DataNotFoundException {
    if (application == null) {
      throw new DataNotFoundException("Application not found.");
    }
    return application;
  }

  private Application save(final Application application) throws ConstraintsViolationException {
    try {
      return applicationRepo.save(application);
    } catch (DataIntegrityViolationException e) {
      logger.error("Integrity exception", e);
      throw new ConstraintsViolationException("Already applied for this offer", e);
    }
  }

  private void validateApplication(final Application application)
      throws ConstraintsViolationException {
    if (application.getRelatedOffer() == null) {
      logger.warn("Offer not found");
      throw new ConstraintsViolationException("Offer not found.");
    }
  }
}
