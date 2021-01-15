package com.vaccnow.vaccinationplans.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ActiveStatus {

	INACTIVE(0),
	ACTIVE(1),
	ARCHIEVED(2);
	
	private Integer activeStatus;
	
	ActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Integer value() {
		return this.activeStatus;
	}
	
	public static Optional<ActiveStatus> valueOf(Integer value) {
		
		return Arrays.stream(values())
            .filter(
            		activeStatusKey -> activeStatusKey.value() == value)
            .findFirst();
    }
}
