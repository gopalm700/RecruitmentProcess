package com.heavenhr.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "offer")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Offer {

  @Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false, updatable = false)
  private Long id;

  @Column(name = "job_title", unique = true)
  private String jobTitle;

  @Column(name = "number_of_applications")
  private Integer numberOfApplications;

  @Column(name = "start_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar startDate;

  @OneToMany(mappedBy = "relatedOffer", cascade = {ALL}, fetch = EAGER)
  private final List<Application> applications = new ArrayList<>();

}
