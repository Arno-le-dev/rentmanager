package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;
//	public static ClientService instance;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}


	public long create(Client client) throws ServiceException {
		
		// on fait appelle à la fonction create de clientDao en transmettant le client créé dans le servlet
		try {
			return this.clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace(); 
		}
		return 0; 
	} 
	
	public long edit(Client client) throws ServiceException {
		try {
			return this.clientDao.edit(client); 
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0; 
	}

	public long delete(Integer id)throws ServiceException {
		try {
			return this.clientDao.delete(id);		
		} catch (DaoException e){
			e.printStackTrace();
		}
		return 0; 
	}
	
	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// TODO: récupérer tous les clients

	}

}
