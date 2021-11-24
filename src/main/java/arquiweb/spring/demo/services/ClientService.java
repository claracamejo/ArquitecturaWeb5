package arquiweb.spring.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquiweb.spring.demo.dtos.ClientReportDTO;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.repositories.ClientRepository;

/**
*   Este servicio es llamado desde el controlador de Client y decide a que 
*  repositorio llamar dependiendo la instancia creada de la aplicacion
*
*/

@Service
public
class ClientService {
	@Autowired
	private ClientRepository clients;
	
	/**
	 * @return
	 * Retorna un listado de Clientes
	 */
	public List<Client> getClients() {
		return this.clients.findAll();
	}
	
	/**
	 * @param dni
	 * @return
	 * Retorna un listado de los clientes por dni
	 */
	public Optional<Client> getClient(Integer dni) {
		return Optional.of(this.clients.findById(dni).get());
	}
	
	/**
	 * @param c
	 * @return
	 * Retorna true si el Cliente fue insertado con exito
	 */
	@Transactional
	public boolean insert(Client c) {
		this.clients.save(c);
		return true;
	}
	
	/**
	 * @param dni
	 * @return
	 * Retorna true si el Cliente fue eliminado con exito
	 */
	@Transactional
	public boolean delete(int dni) {
		this.clients.deleteById(dni);
		return true;
	}
	
	/**
	 * @param name
	 * @param lastname
	 * @param address
	 * @param dni
	 * @return
	 * Retorna true si el Cliente fue actualizado con exito
	 */
	@Transactional
	public boolean update(String name, String lastname,String address, int dni) {
		this.clients.updateClient(name, lastname,address, dni);
		return true;
	}
	
	/**
	 * @return
	 * Retorna el listado del reporte de Clientes
	 */
	public List<ClientReportDTO> getClientsReport() {
		return this.clients.clientReport();
	}
}
