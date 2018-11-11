package com.heavenhr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.heavenhr.entity.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {

  @Query("SELECT a FROM Application a WHERE a.relatedOffer.id=?1")
  List<Application> findByOffer(Long id);
}
