package com.heavenhr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;

import com.heavenhr.dto.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application",
    uniqueConstraints = @UniqueConstraint(columnNames = {"offer", "candidate_email"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Application {
  @Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false, updatable = false)
  private Long id;

  @Email
  @Column(name = "candidate_email")
  private String candidateEmail;

  @Column(name = "resume_text")
  private String resumeText;

  @Enumerated(EnumType.STRING)
  @Column(name = "application_status")
  private ApplicationStatus applicationStatus;

  @JoinColumn(name = "offer", updatable = false)
  @ManyToOne
  private Offer relatedOffer;
}
