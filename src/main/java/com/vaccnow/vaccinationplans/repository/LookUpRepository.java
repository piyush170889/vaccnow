package com.vaccnow.vaccinationplans.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccnow.vaccinationplans.domain.LookUp;

@Repository
public interface LookUpRepository extends IBaseRepository<LookUp>, JpaRepository<LookUp, Long> {

	List<LookUp> findByActiveStatusAndReferenceIn(Integer activeStatus, List<String> referenceList, Sort by);

}
