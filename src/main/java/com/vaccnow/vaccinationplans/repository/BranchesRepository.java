package com.vaccnow.vaccinationplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccnow.vaccinationplans.domain.Branches;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Long>, IBaseRepository<Branches> {

}
