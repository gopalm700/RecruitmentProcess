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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.heavenhr.converter.OfferConverter;
import com.heavenhr.dto.OfferDto;
import com.heavenhr.entity.Offer;
import com.heavenhr.exception.DataNotFoundException;
import com.heavenhr.service.OfferService;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private OfferService service;

  @MockBean
  private OfferConverter converter;


  @Test
  public void testGetOfferById() throws Exception {
    Mockito.when(converter.entityToDto(Mockito.any(Offer.class)))
        .thenReturn(OfferDto.builder().id(1l).jobTitle("sse").build());

    mvc.perform(MockMvcRequestBuilders.get("/v1/offer/1").accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("offer.jobTitle", is("sse"))).andExpect(jsonPath("offer.id", is(1)));
  }

  @Test
  public void testEntityNotFoundException() throws Exception {

    Mockito.when(service.findById(Mockito.anyLong())).thenThrow(DataNotFoundException.class);
    mvc.perform(MockMvcRequestBuilders.get("/v1/offer/10").accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void testGetOffers() throws Exception {
    List<Offer> offerList = new ArrayList<Offer>();
    offerList.add(Offer.builder().id(1l).build());
    offerList.add(Offer.builder().id(2l).build());
    Mockito.when(service.findAll()).thenReturn(offerList);
    Mockito.when(converter.entityToDto(Mockito.any(Offer.class)))
        .thenReturn(OfferDto.builder().id(1l).build());
    mvc.perform(MockMvcRequestBuilders.get("/v1/offer").accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("offers", hasSize(2)));
  }


  @Test
  public void testGetTrackNumberOfApplicationsById() throws Exception {
    Mockito.when(service.findById(Mockito.anyLong()))
        .thenReturn(Offer.builder().numberOfApplications(1).build());
    mvc.perform(
        MockMvcRequestBuilders.get("/v1/offer/1/applicationcount").accept("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("numberOfApplications", is(1)));
  }


  @Test
  public void testPostCreateOffer() throws Exception {
    Mockito.when(service.createOffer(Mockito.anyString()))
        .thenReturn(Offer.builder().id(1l).jobTitle("SSE").build());
    Mockito.when(converter.entityToDto(Mockito.any(Offer.class)))
        .thenReturn(OfferDto.builder().id(1l).build());
    mvc.perform(MockMvcRequestBuilders.post("/v1/offer").accept("application/json")
        .content("{\"jobTitle\": \"SSE\"}")).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(jsonPath("offer.id", is(1)));
  }
}
