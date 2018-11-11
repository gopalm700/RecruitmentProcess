package com.heavenhr.service.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heavenhr.entity.Offer;
import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;
import com.heavenhr.repository.OfferRepo;
import com.heavenhr.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

  private static Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);

  @Autowired
  private OfferRepo offerRepo;

  @Override
  public Offer createOffer(String jobTitle) throws ConstraintsViolationException {
    Offer offer = Offer.builder().jobTitle(jobTitle).numberOfApplications(0)
        .startDate(Calendar.getInstance()).build();
    return save(offer);
  }

  @Override
  public Offer findById(final Long offerId) throws DataNotFoundException {
    return checkOffer(offerRepo.findOne(offerId));
  }

  @Override
  public Offer increaseNumberOfApplications(final Offer offer)
      throws DataNotFoundException, ConstraintsViolationException {
    checkOffer(offer);
    offer.setNumberOfApplications(offer.getNumberOfApplications() + 1);
    return save(offer);
  }

  @Override
  public List<Offer> findAll() {
    return offerRepo.findAll();
  }

  private Offer save(Offer offer) throws ConstraintsViolationException {
    try {
      return offerRepo.save(offer);
    } catch (DataIntegrityViolationException e) {
      logger.warn("Integrity Exception", e);
      throw new ConstraintsViolationException("Some constraints are thrown due to offer creation.");
    }
  }

  private Offer checkOffer(Offer offer) throws DataNotFoundException {
    if (offer == null) {
      logger.warn("Offer not found");
      throw new DataNotFoundException("Offer not found.");
    }
    return offer;
  }
}
