package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {

	private int idClient;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate naissance;

	
	//constructeurs
	public Client(int idClient, String nom, String prenom, String email, LocalDate naissance) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance;
	}
	
	public Client(String nom, String prenom, String email, LocalDate naissance) {
		super(); 
		this.nom = nom; 
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance; 
		
	}
	
	public Client() {
		super();
	}

	//Getter & Setter
	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getnaissance() {
		return naissance;
	}
	public void setnaissance(LocalDate naissance) {
		this.naissance = naissance;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", naissance=" + naissance + "]";
	}
	
	
	
	
	
}
