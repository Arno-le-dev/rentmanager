package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;

@Repository 
public class ClientDao {
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String EDIT_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?";
	private static final String COUNT_CLIENT_QUERY = "SELECT COUNT (id) FROM Client;";
	
	public long create(Client client) throws DaoException {
		
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(CREATE_CLIENT_QUERY);
				
			pstmt.setString(1,client.getNom()); 
			pstmt.setString(2,client.getPrenom()); 
			pstmt.setString(3,client.getEmail());
			
		
			Date birthdate = Date.valueOf(client.getnaissance());
			pstmt.setDate(4,birthdate);
				
			pstmt.executeUpdate(); 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 
	}
	
	public long edit(Client client) throws DaoException {
		
		try {
		Connection conn= ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(EDIT_CLIENT_QUERY);
		
		pstmt.setString(1, client.getNom());
		pstmt.setString(2, client.getPrenom());
		pstmt.setString(3, client.getEmail());
	
		Date birthdate = Date.valueOf(client.getnaissance());
		pstmt.setDate(4,birthdate);
		pstmt.setInt(5, client.getIdClient());
		
		pstmt.executeUpdate();
		
		} catch (SQLException e){
			e.printStackTrace();	 
	}
		return 0;
	
	}

	public long delete(Integer id) throws DaoException {
		try {
			Connection conn=ConnectionManager.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY); 
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 
		
	}

	public Optional<Client> findById(int id) throws DaoException {
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmt.setInt(1, id);
			
			ResultSet rs= pstmt.executeQuery();
			
			rs.next();
			
			String clientLastname=rs.getString("nom");
			String clientFirstname=rs.getString("prenom");
			String clientEmail=rs.getString("email");
			LocalDate clientBirthdate=rs.getDate("naissance").toLocalDate();
			
			Client client = new Client(id,clientLastname,clientFirstname,clientEmail,clientBirthdate);
			
			
			return Optional.of(client);
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return Optional.empty();
	}

	
	public List<Client> findAll() throws DaoException {
		
		List<Client> clients = new ArrayList<Client>();
		try {
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(FIND_CLIENTS_QUERY);
			
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {
			
			int id=rs.getInt("id");
			String clientLastname=rs.getString("nom");
			String clientFirstname=rs.getString("prenom");
			String clientEmail=rs.getString("email");
			LocalDate clientBirthdate=rs.getDate("naissance").toLocalDate();
			
			Client client = new Client(id,clientLastname,clientFirstname,clientEmail,clientBirthdate);
			clients.add(client);		
			}
			
			return clients;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

		 
	}
	
	public long count() throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENT_QUERY);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			int compte = rs.getInt(1);

			return compte;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}
}
