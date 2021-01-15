package com.vaccnow.vaccinationplans.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vaccnow.vaccinationplans.constants.StringConstants;
import com.vaccnow.vaccinationplans.dto.BaseWrapper;
import com.vaccnow.vaccinationplans.dto.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonApiExceptionHandler {

	@ExceptionHandler(value = { ServicesApiException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public BaseWrapper validationException(HttpServletRequest request, HttpServletResponse response,
			ServicesApiException ex) {

		log.error("VN Validation Error:" + ex.getMessage());
		log.error("Stack Trace: ", ex);

		BaseWrapper apiResponse = new BaseWrapper(
				null, 
				new ResponseMessage(
						HttpStatus.BAD_REQUEST.name(), 
				ex.getMessage()));
		
		return apiResponse;
	}


	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public BaseWrapper genericException(HttpServletRequest request, HttpServletResponse response,
			Exception ex) {
		
		if (null != request.getAttribute("javax.servlet.error.status_code")
				&& request.getAttribute("javax.servlet.error.status_code").equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
			log.error("URI in Exception::{}", request.getAttribute("javax.servlet.error.request_uri"));
		}
		log.error("Generic Error:" + ex.getMessage());
		log.error("Stack Trace: ", ex);


		BaseWrapper apiResponse = new BaseWrapper(
				null, 
				new ResponseMessage(
						HttpStatus.INTERNAL_SERVER_ERROR.name(), 
						StringConstants.GENERIC_ERROR_MSSG));
		
		return apiResponse;
	}
}
