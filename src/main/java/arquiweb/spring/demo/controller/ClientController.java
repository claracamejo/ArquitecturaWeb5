package arquiweb.spring.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import arquiweb.spring.demo.dtos.ClientReportDTO;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.services.ClientService;

/**
*  Dado un pedido REST, el controlador de Client atiende el pedido y llama al servicio requerido.
*  Objetivo principal mapear las URL para acceder al recurso necesario.
*/


@RestController
@RequestMapping("/client")
public class ClientController {
private static Logger LOG = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;
	
	/**
	 * @return
	 * Retorna un listado de clientes
	 */
	@GetMapping("")
	public List<Client> getAll() {
		return this.clientService.getClients();
	}
	
	/**
	 * @param c
	 * @return
	 * Retorna true si el Cliente c fue insertado con exito
	 */
	@PostMapping("")
	public ResponseEntity<Client> addClient(@RequestBody Client c) {
		
		boolean ok = this.clientService.insert(c);
		if(!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		System.out.println(c);
		return new ResponseEntity<Client>(c, HttpStatus.OK);
	}
	
	/**
	 * @param id
	 * @return
	 * Retorna el cliente con ese id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getClient(@PathVariable( "id" ) int id) {
		Optional<Client> responseC = this.clientService.getClient(id);
		System.out.println("out " + responseC);
		if (responseC != null) {
			return new ResponseEntity<>(responseC, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(responseC, HttpStatus.OK);
		}
		//return this.clientService.getClient(id);
	}
	
	/**
	 * @param id
	 * @return
	 * Retorna true si el Cliente c fue eliminado con exito
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") int id) {
		boolean ok = this.clientService.delete(id);
		if(!ok ) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
	}
	
	/**
	 * @param id
	 * @param client
	 * @return
	 * Retorna true si el Cliente c fue actualizado con exito
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateClient(@PathVariable( "id" ) int id, @RequestBody Client client) {
		boolean ok = false;
		if(client != null) {
			ok = this.clientService.update(client.getName(), client.getLastname(), client.getAddress(), id);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	/**
	 * @return
	 * Retorna el listado del reporte de Clientes
	 */
	@GetMapping("/report")
	public List<ClientReportDTO> getReport() {
		return this.clientService.getClientsReport();
	}
}

