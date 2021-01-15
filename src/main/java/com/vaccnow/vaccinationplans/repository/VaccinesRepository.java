package com.vaccnow.vaccinationplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccnow.vaccinationplans.domain.Vaccines;

@Repository
public interface VaccinesRepository extends IBaseRepository<Vaccines>, JpaRepository<Vaccines, Long> {

}
