package com.vaccnow.vaccinationplans.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccnow.vaccinationplans.domain.BranchVaccines;
import com.vaccnow.vaccinationplans.domain.Vaccines;

@Repository
public interface BranchVaccinesRepository extends IBaseRepository<BranchVaccines>, JpaRepository<BranchVaccines, Long> {

	@Query("select bv.vaccine from BranchVaccines bv where bv.branch.id=?1 and bv.activeStatus=?2")
	List<Vaccines> findByBranchIdAndActiveStatus(Long branchId, Integer activeStatus, Sort sortBy);

	@Query("select bv.vaccine from BranchVaccines bv where bv.branch.id=?1 and bv.activeStatus=?2")
	List<Vaccines> findByBranchIdAndActiveStatus(Long branchId, Integer activeStatus);
}
