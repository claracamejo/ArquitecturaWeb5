package arquiweb.spring.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import arquiweb.spring.demo.dtos.ClientReportDTO;

import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

/**
*  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
*  En esta clase se mapea la Entidad Client
* 
*  La native query que hicimos como jpa no toma bien el formato 
*  
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
public class Client {
	
	@Id
	private int dni;
	
	@Column
	private String name;
	
	@Column
	private String lastname;
	
	@Column
	private String address;
	
	public Client() {
		super();
	}

	public Client(int dni, String name, String lastname, String address) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDni() {
		return dni;
	}
	@Override
	public String toString() {
		return "Client [dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", address=" + address + "]";
	}
	
	
	
}
