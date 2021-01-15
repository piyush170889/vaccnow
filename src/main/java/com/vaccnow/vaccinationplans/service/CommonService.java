package com.vaccnow.vaccinationplans.service;

import com.vaccnow.vaccinationplans.dto.BaseWrapper;
import com.vaccnow.vaccinationplans.exception.ServicesApiException;

public interface CommonService {

	BaseWrapper getLookUpList(String reference, String term) throws ServicesApiException;
}
