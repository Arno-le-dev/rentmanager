package com.epf.rentmanager.model;


public class Vehicle {

	private int idVehicule;
	private String constructeur;
	private int nbPlaces;
	private String modele; 	
	
	
	//constructeurs 
	
	public Vehicle(int idVehicule, String constructeur, int nbPlaces) {
		super();
		this.idVehicule = idVehicule;
		this.constructeur = constructeur;
		this.nbPlaces = nbPlaces;
	}
	
	public Vehicle(String constructeur, String modele, int nbPlaces) {
		this.constructeur = constructeur; 
		this.modele = modele; 
		this.nbPlaces = nbPlaces; 
	}
	
	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Vehicle() {
		super();
	}

	//getter & setter
	public int getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	@Override
	public String toString() {
		return "Vehicle [idVehicule=" + idVehicule + ", constructeur=" + constructeur + ", nb_places=" + nbPlaces  + "]";
	}
	
}
