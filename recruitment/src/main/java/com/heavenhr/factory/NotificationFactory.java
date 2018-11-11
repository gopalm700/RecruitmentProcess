package com.heavenhr.factory;

import com.heavenhr.service.NotificationService;

/**
 * @author gopal 11-Nov-2018
 *
 */


public interface NotificationFactory {
  public NotificationService get(String name);
}
