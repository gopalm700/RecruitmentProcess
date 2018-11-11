package com.heavenhr.service;

import com.heavenhr.entity.Offer;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;

import java.util.List;

public interface OfferService {


    Offer createOffer(String jobTitle) throws ConstraintsViolationException;

    Offer findById(Long offerId) throws DataNotFoundException;

    Offer increaseNumberOfApplications(Offer offer) throws DataNotFoundException, ConstraintsViolationException;

    List<Offer> findAll();
}
