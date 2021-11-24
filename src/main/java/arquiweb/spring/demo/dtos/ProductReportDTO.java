package arquiweb.spring.demo.dtos;

/**
* Este DTO nace de la necesidad de mostrar determinados datos 
* que son el resultado de la nativeQuery product_report_dto
*/

public class ProductReportDTO {
	
	private int id;
	private String name;
	private Long price;
	private Long totalSold;
	
	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param totalSold
	 * Constructor de la clase ProductReportDTO
	 */
	public ProductReportDTO(int id, String name, Long price, Long totalSold) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalSold = totalSold;
	}

	/**
	 * @return id
	 * Retorna el id de la clase
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * Modifica el id de la clase
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return
	 * Retorna el parametro price de la clase el cual 
	 * hace referencia al precio del producto
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price
	 * Modifica el parametro price de la clase el cual 
	 * hace referencia al precio del producto
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return totalSold
	 * Retorna el parametro totalSold de la clase, el cual hace referencia 
	 * al total de ventas
	 */
	public Long getTotalSold() {
		return totalSold;
	}

	/**
	 * @param totalSold
	 * Modifica el parametro totalSold de la clase, el cual hace referencia 
	 * al total de ventas
	 */
	public void setTotalSold(Long totalSold) {
		this.totalSold = totalSold;
	}

	@Override
	public String toString() {
		return "ProductReportDTO [id=" + id + ", name=" + name + ", price=" + price
				+ ", totalSold=" + totalSold + "]";
	}
	
}
