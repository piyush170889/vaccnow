package com.vaccnow.vaccinationplans.enums;

import java.util.Arrays;
import java.util.Optional;

public enum LookUpDataKeys {

	branches, 
	vaccines, 
	paymentMethods;
	
	private String masterDataKeys;
	
	public String value() {
		return this.masterDataKeys;
	}
	
	public static boolean contains(String value) {
		
		Optional<LookUpDataKeys> masterDataKeysEnum = Arrays.stream(values())
            .filter(
            		masterDataKey -> masterDataKey.name().equals(value))
            .findFirst();
		
		return masterDataKeysEnum.isPresent();
    }
}
