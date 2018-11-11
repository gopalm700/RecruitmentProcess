package com.heavenhr.dto;

import com.heavenhr.entity.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gopal 11-Nov-2018
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotifyObject {

  private Application application;
  private ApplicationStatus applicationStatus;

}
