package com.g6.onlineeyecare.spectacles.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




public class SpectaclesDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int spectaclesId;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Size(min = 3, max = 20)
	private String spectaclesModel;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String spectaclesDescription;
	
	@Min(value = 1,message = "rating cannot be less than 1")
	@Max(value = 5,message = " rating cannot be more than 5")
	private int spectaclesRating;
	
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
	@Override
	public String toString() {
		return "SpectaclesDTO [spectaclesId=" + spectaclesId + ", spectaclesModel=" + spectaclesModel
				+ ", spectaclesDescription=" + spectaclesDescription + ", spectaclesRating=" + spectaclesRating
				+ ", spectaclesCost=" + spectaclesCost + "]";
	}
	
}
