package arquiweb.spring.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.dtos.ClientReportDTO;

/**
 * Repositorio de Client, las operaciones
 * que se declaren en esta interfaz son las que estan.
 * relacionadas con operaciones en la base de datos
 * 
 */


public interface ClientRepository extends JpaRepository<Client, Object> {
	
	/*
	 * Modificar un Client seteando los datos que le fueron pasados por parametro
	 */
	@Modifying
	@Query(value="UPDATE Client SET name = :name, lastname = :lastname, address = :address WHERE dni = :dni", nativeQuery = true)
	public void updateClient(@Param("name") String name, @Param("lastname") String lastname,@Param("address") String address, @Param("dni") int dni);
	
	/*
	 * Devuelve todos los clientes mas el monto total de sus compras.
	 */

	@Query(name = "client_report_dto", nativeQuery = true)
	public List<ClientReportDTO> clientReport();
}
