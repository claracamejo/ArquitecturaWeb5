package arquiweb.spring.demo.dtos;

/**
* Guarda los datos que necesitamos de Client
* para asignarlos a la query que los busca en la base de datos 
*/

public class ClientReportDTO {

	private int dni;
	
	private String name;
	
	private String lastname;
	
	private String address;
	
	private Long total;

	public ClientReportDTO(int dni, String name, String lastname, String address, Long total) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.total = total;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ClientReportDTO [dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", address=" + address
				+ ", total=" + total + "]";
	}
	
	
}
