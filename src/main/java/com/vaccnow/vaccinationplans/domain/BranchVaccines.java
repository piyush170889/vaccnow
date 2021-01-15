package com.vaccnow.vaccinationplans.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "branch_vaccines")
public class BranchVaccines extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_ID")
	private Branches branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VACCINE_ID")
	@OrderBy(value = "vaccineName ASC")
	private Vaccines vaccine;
}
