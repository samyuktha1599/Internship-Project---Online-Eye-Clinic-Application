package com.g6.onlineeyecare.spectacles.dto;

public class SpectaclesResponseDTO {

	private int spectaclesId;
	private String spectaclesModel;
	private String spectaclesDescription;
	private int spectaclesRating;
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
		return "SpectaclesResponseDTO [spectaclesId=" + spectaclesId + ", spectaclesModel=" + spectaclesModel
				+ ", spectaclesDescription=" + spectaclesDescription + ", spectaclesRating=" + spectaclesRating
				+ ", spectaclesCost=" + spectaclesCost + "]";
	}

	
}
