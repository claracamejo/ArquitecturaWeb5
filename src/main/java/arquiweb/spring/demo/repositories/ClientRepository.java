package arquiweb.spring.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.dtos.ClientReportDTO;

/**
 * Repositorio de Client, las operaciones que se declaren en esta interfaz son las que estan
 * relacionadas con las consultas a la base de datos de la tabla de Client
 */


public interface ClientRepository extends JpaRepository<Client, Object> {
	
	/**
	 * Modificar un Client seteando los datos que le fueron pasados por parametro
	 * @param name: nombre del cliente a cambiar
	 * @param lastname: Apellido del cliente a modificar
	 * @param address: direccion del cliente a actualizar
	 * @param dni: dni del cliente que se quiere modificar
	 */
	@Modifying
	@Query(value="UPDATE Client SET name = :name, lastname = :lastname, address = :address WHERE dni = :dni", nativeQuery = true)
	public void updateClient(@Param("name") String name, @Param("lastname") String lastname,@Param("address") String address, @Param("dni") int dni);
	
	/**
	 * Devuelve todos los clientes mas el monto total de sus compras.
	 * @return Lista de clientes con el formato de la clase @ClienteReportDTO
	 */
	@Query(name = "client_report_dto", nativeQuery = true)
	public List<ClientReportDTO> clientReport();
}
