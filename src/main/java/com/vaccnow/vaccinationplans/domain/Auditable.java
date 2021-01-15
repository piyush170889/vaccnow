package com.vaccnow.vaccinationplans.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class Auditable {

    @Column(name = "CREATED_BY", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "MODIFIED_BY", nullable = false, length = 50)
    private String modifiedBy;

    @Column(name = "CREATED_TS", nullable = false)
    private Long createdTs;

    @Column(name = "MODIFIED_TS", nullable = false)
    private Long modifiedTs;

    @Column(name = "ACTIVE_STATUS", nullable = false, length = 4)
    private Integer activeStatus;
    
    public Auditable(String createdBy, String modifiedBy, Long createdTs, Long modifiedTs) {
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdTs = createdTs;
		this.modifiedTs = modifiedTs;
	}
    
    public Auditable(String createdBy, String modifiedBy, Long createdTs, Long modifiedTs, Integer activeStatus) {
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdTs = createdTs;
		this.modifiedTs = modifiedTs;
		this.activeStatus = activeStatus;
	}
    
    public Auditable(String createdBy, Long createdTs) {
		this.createdBy = createdBy;
		this.createdTs = createdTs;
	}
}

