package arquiweb.spring.demo.dtos;

/**
* Este DTO nace de la necesidad de mostrar determinados datos 
* que son el resultado de la nativeQuery client_report_dto
*/

public class ClientReportDTO {

	private int dni;
	
	private String name;
	
	private String lastname;
	
	private String address;
	
	private Long total;

	/**
	 * 
	 * @param dni
	 * @param name
	 * @param lastname
	 * @param address
	 * @param total
	 * 
	 * Constructor de la clase ClientReportDTO
	 */
	public ClientReportDTO(int dni, String name, String lastname, String address, Long total) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.total = total;
	}

	/**
	 * @return dni
	 * Retorna el parametro dni de la clase
	 */
	public int getDni() {
		return dni;
	}

	/**
	 * @param dni
	 * Modifica el parametro dni de la clase 
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}

	/**
	 * @return name
	 * Retorna el parametro name de la clase
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * Modifica el parametro name de la clase
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return lastname
	 * Retorna el parametro lastname de la clase
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 * Modifica el parametro lastname de la clase
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return address
	 * Retorna el parametro lastname de la clase
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 * Modifica el parametro address de la clase que refiere a la direccion 
	 * del reporte
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return total
	 * Modifica el parametro total de la clase que hace referencia al total 
	 * del saldo
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total
	 * Modifica el parametro total de la clase 
	 * que hace referencia al total del saldo
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ClientReportDTO [dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", address=" + address
				+ ", total=" + total + "]";
	}
	
	
}
