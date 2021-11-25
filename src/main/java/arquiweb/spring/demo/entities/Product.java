package arquiweb.spring.demo.entities;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import arquiweb.spring.demo.dtos.ClientReportDTO;
import arquiweb.spring.demo.dtos.ProductReportDTO;

/**
*  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
*  En esta clase se mapea la Entidad Product
*  
*  La entidad Product tiene los datos de los productos que tiene el sistema para vender, por lo que cuenta
*  con id, nombre, precio y stock
*
*  La native query que usamos es porque tuvimos problemas con el formato de respuesta de jpa, que
*  no nos dejaba relacionar el resultado con el dto, por lo que decidimos realizarlo de esta manera   
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
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int stock;
	private Long price;
	
	/**
	 * Constructor por default de Product
	 */
	public Product() {
		super();
	}
	
	/**
	 * Contructor que se utiliza para generar el producto con datos.
	 * @param name: nombre del nuevo producto
	 * @param stock: stock que va a tener disponible
	 * @param price: precio al que se va a vender
	 */
	public Product(String name, int stock, Long price) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	/**
	 * @return name: nombre del producto
	 */
	public String getName() {
		return name;
	}
	/**
	 * Modifica el nombre del producto
	 * @param name: nuevo nombre del producto
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return stock disponible del producto
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * @param stock: nuevo stock disponible del producto
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/**
	 * @return precio actual del producto
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * Modifica el precio del producto
	 * @param price: nuevo precio
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	/**
	 * @return id o Clave primaria del producto
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", stock=" + stock + ", price=" + price + "]";
	}
	
}
