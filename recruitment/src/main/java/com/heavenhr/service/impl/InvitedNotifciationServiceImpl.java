package com.heavenhr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.heavenhr.dto.NotifyObject;
import com.heavenhr.service.NotificationService;

/**
 * @author gopal 11-Nov-2018
 *
 */
@Component(value = "INVITED")
public class InvitedNotifciationServiceImpl implements NotificationService {

  private static Logger logger = LoggerFactory.getLogger(InvitedNotifciationServiceImpl.class);


  @Override
  public void notifyEvent(NotifyObject data) {
    logger.info("Application status changed for candidate {} to status {}",
        data.getApplication().getCandidateEmail(), data.getApplicationStatus().name());
  }

}
