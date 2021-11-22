package arquiweb.spring.demo.dtos;

/**
* Guarda los datos que necesitamos de Product
* para asignarlos a la query que los busca en la base de datos 
*/

public class ProductReportDTO {
	
	private int id;
	private String name;
	private Long price;
	private Long totalSold;
	
	public ProductReportDTO(int id, String name, Long price, Long totalSold) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalSold = totalSold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(Long totalSold) {
		this.totalSold = totalSold;
	}

	@Override
	public String toString() {
		return "ProductReportDTO [id=" + id + ", name=" + name + ", price=" + price
				+ ", totalSold=" + totalSold + "]";
	}
	
}
