package com.vaccnow.vaccinationplans.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository<T> {

	List<T> findByActiveStatus(int activeStatus, Sort sortingOrder);
}
