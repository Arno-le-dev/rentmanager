package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;


@Service
public class VehicleService {

	private VehicleDao vehicleDao;
//	public static VehicleService instance;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		// on fait appelle à la fonction create de vehicleDao en transmettant le véhicule créé dans le servlet
				try {
					return this.vehicleDao.create(vehicle);
				} catch (DaoException e) {
					e.printStackTrace(); 
				}
				return 0; 	
	}
	
	
	public long edit(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.edit(vehicle); 
		}catch (DaoException e) {
			e.printStackTrace(); 
		}
		return 0; 
	}
	
	public long delete(Integer id)throws ServiceException {
		try {
			return this.vehicleDao.delete(id);		
		} catch (DaoException e){
			e.printStackTrace();
		}
		return 0; 
	}

	
	public Vehicle findById(long id) throws ServiceException {
		return null;
		// TODO: récupérer un véhicule par son id
		
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
		// TODO: récupérer tous les clients
		
	}
	
}
