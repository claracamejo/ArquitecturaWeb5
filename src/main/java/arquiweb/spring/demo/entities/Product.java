package arquiweb.spring.demo.entities;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import arquiweb.spring.demo.dtos.ClientReportDTO;
import arquiweb.spring.demo.dtos.ProductReportDTO;

/**
*  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
*  En esta clase se mapea la Entidad Product
*
*  La native query que hicimos como jpa no toma bien el formato 
*   
*/

@Entity
@NamedNativeQuery(
	    name = "product_report_dto",
	    query = "SELECT p.id, p.name, p.price, SUM(bp.quantity) as total "
	    		+ " FROM Product p JOIN Bill_Product bp ON p.id = bp.id_product "
	    		+ " GROUP BY p.id ORDER BY total desc LIMIT 1",
	    resultSetMapping = "productReport_dto"
	)
@SqlResultSetMapping(
    name = "productReport_dto",
    classes = @ConstructorResult(
        targetClass = ProductReportDTO.class,
        columns = {
            @ColumnResult(name = "id", type = Integer.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "price", type = Long.class),
            @ColumnResult(name = "total", type = Long.class)
        }
    )
)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int stock;
	private Long price;
	
	public Product() {
		super();
	}
	
	public Product(String name, int stock, Long price) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", stock=" + stock + ", price=" + price + "]";
	}
	
}
