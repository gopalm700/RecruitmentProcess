package com.heavenhr.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.heavenhr.converter.ApplicationConverter;
import com.heavenhr.dto.ApplicationDto;
import com.heavenhr.dto.ApplicationStatus;
import com.heavenhr.entity.Application;
import com.heavenhr.entity.Offer;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.service.ApplicationService;


@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ApplicationService applicationService;

  @MockBean
  private ApplicationConverter applicationConverter;

  @Test
  public void testPostCreateApplication() throws Exception {

    Mockito.when(applicationService.createApplication(Mockito.any(Application.class)))
        .thenReturn(Application.builder().id(1l).build());
    Mockito.when(applicationConverter.entityToDto(Mockito.any(Application.class)))
        .thenReturn(ApplicationDto.builder().id(1l).build());
    mvc.perform(MockMvcRequestBuilders.post("/v1/application")
        .contentType(MediaType.APPLICATION_JSON).content(
            "{\"candidateEmail\": \"gopalm700@40gmail.com\",\"offer\": {\"id\": 1}, \"resumeText\":\"adsf\"}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(jsonPath("application.id", is(1)));
  }

  @Test
  public void testThrowEntityNotFoundIfNoOfferWhenPostCreateApplication() throws Exception {

    Mockito.when(applicationService.createApplication(Mockito.any(Application.class)))
        .thenThrow(ConstraintsViolationException.class);
    mvc.perform(
        MockMvcRequestBuilders.post("/v1/application").contentType(MediaType.APPLICATION_JSON)
            .content("{\"candidateEmail\": \"gopalm700@40gmail.com\", \"resumeText\": \"adsf\"}"))
        .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
  }


  @Test
  public void testGetApplicationById() throws Exception {


    Mockito.when(applicationConverter.entityToDto(Mockito.any(Application.class)))
        .thenReturn(ApplicationDto.builder().id(1l).build());
    mvc.perform(MockMvcRequestBuilders.get("/v1/application/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("application.id", is(1)));
  }


  @Test
  public void testGetApplicationsByOfferId() throws Exception {

    List<Application> applicationList = new ArrayList<>();
    applicationList.add(Application.builder().id(1l).relatedOffer(Offer.builder().id(1l).build())
        .candidateEmail("c@g.c").build());
    applicationList.add(Application.builder().id(1l).relatedOffer(Offer.builder().id(2l).build())
        .candidateEmail("x@g.c").build());

    Mockito.when(applicationService.findAllByOfferId(Mockito.anyLong()))
        .thenReturn(applicationList);
    Mockito.when(applicationConverter.entityToDto(Mockito.any(Application.class)))
        .thenReturn(ApplicationDto.builder().id(1l).build());

    mvc.perform(MockMvcRequestBuilders.get("/v1/application/offer/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("applications", hasSize(2)));
  }

  @Test
  public void testPutUpdateApplicationStatus() throws Exception {
    Mockito.when(applicationConverter.entityToDto(Mockito.any(Application.class))).thenReturn(
        ApplicationDto.builder().id(1l).applicationStatus(ApplicationStatus.HIRED).build());

    mvc.perform(MockMvcRequestBuilders.put("/v1/application/1")
        .contentType(MediaType.APPLICATION_JSON).content("HIRED"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("application.applicationStatus", is("HIRED")));

  }

}
