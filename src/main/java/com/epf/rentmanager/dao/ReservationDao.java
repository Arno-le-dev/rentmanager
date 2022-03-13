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

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.ReservationWeb;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository 
public class ReservationDao {

	/*private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	} */ 
	// fonction inutile avec l'utilisation de Spring (@Repository) 
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";	
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT (id) FROM Reservation;";
	
	
public long create(Reservation reservation) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);

	
			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicule());
			
			Date addDateDebut = Date.valueOf(reservation.getDateStart());
			Date addDateFin = Date.valueOf(reservation.getDateEnd());
			
			
			pstmt.setDate(3, addDateDebut);
			pstmt.setDate(4, addDateFin);

			pstmt.executeUpdate();

			return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
		
	}
		
	
	
	public long delete(Reservation reservation) throws DaoException {
		return 0;
		
	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		return null;
		
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		return null;
		 
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Connection conn=ConnectionManager.getConnection(); 
			PreparedStatement pstmt= conn.prepareStatement(FIND_RESERVATIONS_QUERY); 
			
			ResultSet rs= pstmt.executeQuery(); 
			
			while(rs.next()){
				
				int rentId=rs.getInt("id"); 
				int clientId= rs.getInt("client_id");
				int vehicleId= rs.getInt("vehicle_id");
				LocalDate debutRent=rs.getDate("debut").toLocalDate(); 
				LocalDate finRent=rs.getDate("fin").toLocalDate(); 
				
				Reservation reservation = new Reservation(rentId, clientId, vehicleId, debutRent, finRent);
				reservations.add(reservation); 
				
				System.out.println("Réservation DAO fonctionne");
				System.out.println(reservations);
			}
			return reservations; 
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		 
	}
	

	public Optional<List<ReservationWeb>> affichageWeb() throws DaoException {
		

		try {
			
			List<ReservationWeb> reservationWeb = new ArrayList<ReservationWeb>();
			Connection conn;
			conn = ConnectionManager.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				
			int reservationId = rs.getInt("id");
			int clientId = rs.getInt("client_id");
			int vehicleId = rs.getInt("vehicle_id");
			LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
			LocalDate reservationFin = rs.getDate("fin").toLocalDate();

			
			PreparedStatement pstmtClient = conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmtClient.setInt(1, clientId);		
			ResultSet rsClient = pstmtClient.executeQuery();			
			rsClient.next();
			String nomClientParId = rsClient.getString("nom");
			String prenomClientParId = rsClient.getString("prenom"); 
			
			
			PreparedStatement pstmtVehicle = conn.prepareStatement(FIND_VEHICLE_QUERY);
			pstmtVehicle.setInt(1, vehicleId);
			ResultSet rsVehicle = pstmtVehicle.executeQuery();
			rsVehicle.next();
			String nomVehicleParId = rsVehicle.getString("constructeur");

			
			ReservationWeb reservation = new ReservationWeb(reservationId,nomClientParId,prenomClientParId,nomVehicleParId,reservationDebut,reservationFin);

			reservationWeb.add(reservation);
			}
			return Optional.of(reservationWeb);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return Optional.empty();	
	}
	
public long edit(Reservation reservation) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESERVATION_QUERY);

	
			pstmt.setInt(1, reservation.getIdClient());
			pstmt.setInt(2, reservation.getIdVehicule());
			
			Date addDateDebut = Date.valueOf(reservation.getDateStart());
			Date addDateFin = Date.valueOf(reservation.getDateEnd());
			
			
			pstmt.setDate(3, addDateDebut);
			pstmt.setDate(4, addDateFin);
			pstmt.setInt(5, reservation.getId());

			pstmt.executeUpdate();

			return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
		
	}
	
public long delete(Integer id) throws DaoException {
	
	try {
		Connection conn = ConnectionManager.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY); 
		
		pstmt.setInt(1,  id);
		
		pstmt.executeUpdate(); 
		return 0; 
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return 0; 
}
	
public long count() throws DaoException {

	try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);

		ResultSet rs = pstmt.executeQuery();

		rs.next();
		int compte = rs.getInt(1);

		return compte;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return 0;

}
	
	
}
