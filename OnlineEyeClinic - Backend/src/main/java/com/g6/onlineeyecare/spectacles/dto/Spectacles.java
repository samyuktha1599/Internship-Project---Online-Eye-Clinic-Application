package com.g6.onlineeyecare.spectacles.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Spectacles Entity")
@Entity
@Table(name = "spectacles_info")
public class Spectacles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int spectaclesId;
	@ApiModelProperty(name = "Spectacles Model", required = true)
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Size(min = 3, max = 20)
	private String spectaclesModel;
	@ApiModelProperty(name = "Spectacles description", required = true)
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String spectaclesDescription;
	@Min(value = 1,message = "rating cannot be less than 1")
	@Max(value = 5,message = " rating cannot be more than 5")
	private int spectaclesRating;
	
	@ApiModelProperty(name = "Spectacles cost", required = true)
	@Column
	@Min(value = 1000, message = "cost cannot be less than 1000")
	private double spectaclesCost;


	public int getSpectaclesId() {
		return spectaclesId;
	}

	public void setSpectaclesId(int spectaclesId) {
		this.spectaclesId = spectaclesId;
	}

	public String getSpectaclesModel() {
		return spectaclesModel;
	}

	public void setSpectaclesModel(String spectaclesModel) {
		this.spectaclesModel = spectaclesModel;
	}

	public String getSpectaclesDescription() {
		return spectaclesDescription;
	}

	public void setSpectaclesDescription(String spectaclesDescription) {
		this.spectaclesDescription = spectaclesDescription;
	}

	public double getSpectaclesCost() {
		return spectaclesCost;
	}

	public void setSpectaclesCost(double spectaclesCost) {
		this.spectaclesCost = spectaclesCost;
	}

	
	public int getSpectaclesRating() {
		return spectaclesRating;
	}

	public void setSpectaclesRating(int spectaclesRating) {
		this.spectaclesRating = spectaclesRating;
	}

	public Spectacles() {
		super();
		
	}

	



	public Spectacles(int spectaclesId,
			@NotEmpty(message = "cannot be left empty") @Size(min = 3, max = 20) String spectaclesModel,
			@NotEmpty(message = "cannot be left empty") String spectaclesDescription,
			@Min(value = 1, message = "rating cannot be less than 1") @Max(value = 5, message = " rating cannot be more than 5") int spectaclesRating,
			@Min(value = 1000, message = "cost cannot be less than 1000") double spectaclesCost) {
		super();
		this.spectaclesId = spectaclesId;
		this.spectaclesModel = spectaclesModel;
		this.spectaclesDescription = spectaclesDescription;
		this.spectaclesRating = spectaclesRating;
		this.spectaclesCost = spectaclesCost;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + spectaclesId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spectacles other = (Spectacles) obj;
		if (spectaclesId != other.spectaclesId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Spectacles [spectaclesId=" + spectaclesId + ", spectaclesModel=" + spectaclesModel
				+ ", spectaclesDescription=" + spectaclesDescription + ", spectaclesRating=" + spectaclesRating
				+ ", spectaclesCost=" + spectaclesCost + "]";
	}

	

}
