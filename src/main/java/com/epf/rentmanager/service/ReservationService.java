package com.epf.rentmanager.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.ReservationWeb;


@Service
public class ReservationService {

	private ReservationDao reservationDao; 
	
	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
public List<ReservationWeb> affichageWeb() throws ServiceException {
		
		try {
		return this.reservationDao.affichageWeb().get();
		} catch (DaoException e) {
		e.printStackTrace();
		}
		return null;
		 
	}

public long create(Reservation reservation) throws ServiceException {
	try {
		return this.reservationDao.create(reservation);
		} catch (DaoException e) {
		e.printStackTrace();
		}
	return 0;
	
	}


public long edit(Reservation reservation) throws ServiceException{
	try {
		return this.reservationDao.edit(reservation); 
	} catch (DaoException e) {
		e.printStackTrace(); 
	}
	return 0; 
}

public long delete(Integer id) throws ServiceException{
	try {
		return this.reservationDao.delete(id); 
	} catch (DaoException e) {
		e.printStackTrace(); 
	}
	return 0; 
}

public long count() {
	try {
		return this.reservationDao.count();
	} catch (DaoException e) {
		e.printStackTrace();
	}
	return 0;
	}

}
