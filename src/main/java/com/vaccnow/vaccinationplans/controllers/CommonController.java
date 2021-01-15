package com.vaccnow.vaccinationplans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaccnow.vaccinationplans.constants.RestUrlConstants;
import com.vaccnow.vaccinationplans.dto.BaseWrapper;
import com.vaccnow.vaccinationplans.exception.ServicesApiException;
import com.vaccnow.vaccinationplans.service.CommonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = {
		RestUrlConstants.VN_COMMON
})
@Api(value = "Common API's used all over the application of VaccNow")
public class CommonController {

	@Autowired private CommonService commonService;
	
	
    @GetMapping(value = {
    		RestUrlConstants.PING
    })
    public BaseWrapper pingTest() {
    	
    	return new BaseWrapper("Service response sent successfully!!!");
    }
    
    
    @ApiOperation(
    		value = "Api to get look up data for any dropdown, master table or location info.",
    		responseContainer = "Map")
    @GetMapping(value= {
    		RestUrlConstants.VN_LOOKUP
    })
    public BaseWrapper getLookUpList(
    		@ApiParam(required = true, value = "Look up group/master data group name.") @RequestParam(value="reference", required = false) String reference,
    		@ApiParam(required = false, value = "Search term") @RequestParam(value="term", required = false) String term) throws ServicesApiException {
    	
    	return commonService.getLookUpList(reference, term);
    }
}
