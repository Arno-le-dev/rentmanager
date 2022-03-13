package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {
	
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String EDIT_VEHICLES_QUERY = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id=?;";
	private static final String COUNT_VEHICLE_QUERY = "SELECT COUNT (id) FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);
			
			pstmt.setString(1, vehicle.getConstructeur()); 
			pstmt.setInt(2, vehicle.getNbPlaces()); 
			
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	
	public long edit(Vehicle vehicle) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(EDIT_VEHICLES_QUERY); 
			
			
			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setInt(2, vehicle.getNbPlaces());
			pstmt.setInt(3, vehicle.getIdVehicule());
			pstmt.executeUpdate(); 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 
	}
	
	
	public long delete(Integer id) throws DaoException {
		try {
			Connection conn=ConnectionManager.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY); 
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 
		
	}

	public Optional<Vehicle> findById(long id) throws DaoException {
		return null;
	}

	public List<Vehicle> findAll() throws DaoException {
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>(); 
		try {
			Connection conn= ConnectionManager.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);
			
			ResultSet rs= pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				int id=rs.getInt("id"); 
				String constructeurVehicle = rs.getString("constructeur"); 
				int nbPlace = rs.getInt("nb_places"); 
				
				Vehicle vehicle = new Vehicle(id, constructeurVehicle, nbPlace); 
				vehicles.add(vehicle); 
			}
			return vehicles; 
		}catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return null; 
		
	}
	
	public long count() throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLE_QUERY);

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