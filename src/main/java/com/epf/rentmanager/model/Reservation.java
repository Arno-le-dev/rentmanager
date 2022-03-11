package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	


	private int idClient;
	private int idVehicule;	
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private int id;

	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdVehicule() {
		return idVehicule;
	}
	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}
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
	
	
	public Reservation(int id, int idClient, int idVehicule, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.idClient = idClient;
		this.idVehicule = idVehicule;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.id = id;
	}
	


	
	public String toString() {
		 return id+","+idClient+","+idVehicule+","+dateStart.toString()+","+dateEnd.toString();
	 }
	
	

	
}