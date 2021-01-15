package com.vaccnow.vaccinationplans.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vaccnow.vaccinationplans.constants.StringConstants;
import com.vaccnow.vaccinationplans.domain.LookUp;
import com.vaccnow.vaccinationplans.dto.BaseWrapper;
import com.vaccnow.vaccinationplans.enums.ActiveStatus;
import com.vaccnow.vaccinationplans.enums.Fields;
import com.vaccnow.vaccinationplans.enums.LookUpDataKeys;
import com.vaccnow.vaccinationplans.exception.ServicesApiException;
import com.vaccnow.vaccinationplans.repository.BranchVaccinesRepository;
import com.vaccnow.vaccinationplans.repository.BranchesRepository;
import com.vaccnow.vaccinationplans.repository.LookUpRepository;
import com.vaccnow.vaccinationplans.repository.VaccinesRepository;
import com.vaccnow.vaccinationplans.service.CommonService;
import com.vaccnow.vaccinationplans.utility.CheckUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackOn = Throwable.class)
public class CommonServiceImpl implements CommonService {

	@Autowired private LookUpRepository lookUpRepository;
	@Autowired private BranchesRepository branchesRepository;
	@Autowired private VaccinesRepository vaccinesRepository;
	@Autowired private BranchVaccinesRepository branchVaccinesRepository;
	
	@Override
	public BaseWrapper getLookUpList(String reference, 
			String term) throws ServicesApiException {

		List<LookUp> lookUpList = new ArrayList<>();
		Map<String, Object> finalLookUpMap = new HashMap<>();
		
		if (CheckUtil.hasValue(reference)) {

			List<String> referenceList = Arrays.asList(reference.split(StringConstants.DELIMITER));

			if (!referenceList.isEmpty()) {
				log.debug("referenceList = {}", referenceList);

				// Group all the references to masters
				groupLookupByMasterReference(finalLookUpMap, referenceList, term);
			
			
				// Fetch all references from look up table
				lookUpList = lookUpRepository.findByActiveStatusAndReferenceIn(
					ActiveStatus.ACTIVE.value(), 
					referenceList, 
					Sort.by(
							Direction.ASC, Fields.value.name()));

			}
		} else {
			lookUpList = lookUpRepository.findByActiveStatus(
					ActiveStatus.ACTIVE.value(), 
					Sort.by(
							Direction.ASC, Fields.value.name()));
		}
		
		//Put all look up data by reference list
		finalLookUpMap.putAll(
				groupLookUpByReference(lookUpList));
		
		return new BaseWrapper(finalLookUpMap);
	}


	private Map<String, List<LookUp>> groupLookUpByReference(List<LookUp> lookUpList) {
		
		Map<String, List<LookUp>> lookUpMap = new HashMap<>();
		
		lookUpList.forEach(
				(lookUp) -> {
					String referenceName = lookUp.getReference();
			
					List<LookUp> lookUpForReferenceList = lookUpMap.get(referenceName);
					if (!CheckUtil.hasValue(lookUpForReferenceList))
						lookUpForReferenceList = new ArrayList<>();
					
					lookUpForReferenceList.add(lookUp);
					
					lookUpMap.put(referenceName, lookUpForReferenceList);
		});
		
		return lookUpMap;
	}

	private void groupLookupByMasterReference(
			Map<String, Object> finalLookUpMap, 
			List<String> referenceList, 
			String term) throws ServicesApiException {
		
		int referenceListSize = referenceList.size();
		
		for (int i=0; i< referenceListSize; i++) {
			String referenceInIteration = referenceList.get(i);
			List<?> masterList = new ArrayList<>();

			if (CheckUtil.hasValue(referenceInIteration) 
					&& LookUpDataKeys.contains(referenceInIteration)) {
				switch (LookUpDataKeys.valueOf(referenceInIteration)) {
				case branches:
					masterList = branchesRepository
								.findByActiveStatus(
										ActiveStatus.ACTIVE.value(), 
										Sort.by(
												Direction.ASC, Fields.branchName.name()));
					break;
	
				case vaccines:
					masterList = !CheckUtil.hasValue(term) ? 
							vaccinesRepository.findByActiveStatus(
									ActiveStatus.ACTIVE.value(), 
									Sort.by(
											Direction.ASC, Fields.vaccineName.name())) 
							: branchVaccinesRepository.findByBranchIdAndActiveStatus(
									Long.valueOf(term.trim()), 
									ActiveStatus.ACTIVE.value());
					break;
					
				default:
					break;
				}
			
			}
			
			if (!masterList.isEmpty())
				finalLookUpMap.put(referenceInIteration, masterList);
		}
	}

}
