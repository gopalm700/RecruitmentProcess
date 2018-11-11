package com.heavenhr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heavenhr.entity.Offer;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {

}
