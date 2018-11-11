package com.heavenhr.response;

import com.heavenhr.dto.ApplicationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

  private ApplicationDto application;
}
