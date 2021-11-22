package arquiweb.spring.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquiweb.spring.demo.dtos.ClientReportDTO;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.repositories.ClientRepository;

/**
*  Este es llamado desde el controlador de Client y sabe a que Repositorio tiene que llamar 
*  dependiendo la instancia creada de la aplicación.
*
*/

@Service
public
class ClientService {
	@Autowired
	private ClientRepository clients;
	
	public List<Client> getClients() {
		return this.clients.findAll();
	}
	
	public Client getClient(int dni) {
		return this.clients.getById(dni);
	}
	
	@Transactional
	public boolean insert(Client c) {
		this.clients.save(c);
		return true;
	}
	@Transactional
	public boolean delete(int dni) {
		this.clients.deleteById(dni);
		return true;
	}
	@Transactional
	public boolean update(String name, String lastname,String address, int dni) {
		this.clients.updateClient(name, lastname,address, dni);
		return true;
	}
	
	public List<ClientReportDTO> getClientsReport() {
		return this.clients.clientReport();
	}
}
