package com.epf.rentmanager.model;

import java.time.LocalDate;

public class ReservationWeb {
	
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private int id;
	
	private String nomClient;
	private String nomVehicule;
	public LocalDate getDateStart() {
		return dateStart;
	}
	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}
	public LocalDate getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getNomVehicule() {
		return nomVehicule;
	}
	public void setNomVehicule(String nomVehicule) {
		this.nomVehicule = nomVehicule;
	}
	
	
	public  ReservationWeb (int id, String nomClient, String nomVehicule, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.nomClient = nomClient;
		this.nomVehicule = nomVehicule;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.id = id;
	}
	
	
	

}
