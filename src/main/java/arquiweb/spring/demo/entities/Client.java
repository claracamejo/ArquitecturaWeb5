package arquiweb.spring.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import arquiweb.spring.demo.dtos.ClientReportDTO;

import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

/**
*  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
*  En esta clase se mapea la Entidad Client
* 
*  En esta entidad guardamos los datos de los clientes que quieran usar el sistema
*  
*  La native query que usamos es porque tuvimos problemas con el formato de respuesta de jpa, que
*  no nos dejaba relacionar el resultado con el dto, por lo que decidimos realizarlo de esta manera  
*/

@Entity
@NamedNativeQuery(
	    name = "client_report_dto",
	    query = "Select c.dni, c.name, c.lastname, c.address, SUM(b.total) as total"
	    			+ " from Client c join Bill b ON c.dni = b.dni"
	    			+ " group by c.dni",
	    resultSetMapping = "clientReport_dto"
	)
@SqlResultSetMapping(
    name = "clientReport_dto",
    classes = @ConstructorResult(
        targetClass = ClientReportDTO.class,
        columns = {
            @ColumnResult(name = "dni", type = Integer.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "lastname", type = String.class),
            @ColumnResult(name = "address", type = String.class),
            @ColumnResult(name = "total", type = Long.class)
        }
    )
)
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Client {
	
	@Id
	private int dni;
	
	@Column
	private String name;
	
	@Column
	private String lastname;
	
	@Column
	private String address;
	
	/**
	 * Contructor por defecto de Client
	 */
	public Client() {
		super();
	}

	/**
	 * Contructor de cliente, se inicializa la instancia del mismo con los siguientes datos:
	 * @param dni: identificacion unica del cliente
	 * @param name: nombre del cliente
	 * @param lastname: apellido del cliente
	 * @param address: direccion del cliente
	 */
	public Client(int dni, String name, String lastname, String address) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
	}
	/**
	 * @return name: nombre actual del cliente
	 */
	public String getName() {
		return name;
	}
	/**
	 * Modifica el nombre del cliente
	 * @param name: nuevo nombre del cliente
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return apellido del cliente
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * Modifica el apellido
	 * @param lastname: nuevo apellido del cliente
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * Devuelve la direccion actual del cliente
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Modifica la direccion que tiene la instancia de cliente
	 * @param address: nueva direccion del cliente
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Devuelve el DNI, que seria el id o clave primaria que identifica 
	 * inequivocamente a un solo cliente
	 * @return dni
	 */
	public int getDni() {
		return dni;
	}
	@Override
	public String toString() {
		return "Client [dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", address=" + address + "]";
	}
	
	
	
}
