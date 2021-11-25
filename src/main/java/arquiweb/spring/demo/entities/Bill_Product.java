package arquiweb.spring.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
 *  En esta clase se mapea la Bill_Product, que esta directamente relacionada con la entidad de Bill.
 *  
 *  Decidimos crear una tabla FacturaProducto para manejar las relaciones entre Producto y Factura,
 *  y poder asignarles campos que creemos necesarios para el funcionamiento del sistema
 */

@Entity
public class Bill_Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;	
	@ManyToOne
	@JoinColumn(name="id_bill")
	private Bill bill;
	private Long price;
	private int quantity;
	
	/**
	 * Contructor por default de Bill_Product
	 */
	public Bill_Product() {
		super();
	}
	
	/**
	 * Constructor que inicializa la instancia de Bill_Product con los siguentes datos
	 * @param product: producto asociado a la compra
	 * @param bill: factura a la que pertence este producto
	 * @param price: precio actual del producto
	 * @param quantity: cantidad del producto que se compro
	 */
	public Bill_Product(Product product, Bill bill, Long price, int quantity) {
		super();
		this.product = product;
		this.bill = bill;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * Devuelve el objeto Producto de esta instancia
	 * @return Product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * Modifica el producto asociado a esta compra
	 * @param product: nuevo producto a asociar
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * Devuelve la cabecera de la factura a la que esta asociada este producto
	 * @return Bill padre de esta clase
	 */
	public Bill getBill() {
		return bill;
	}
	/**
	 * Modifica la factura padre asociada esta instancia de Bill_Product
	 * @param bill: nueva factura a asociar a Bill_Product
	 */
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	/**
	 * Devuelve el precio al que se vendio el producto
	 * @return precio del producto al momento de la venta
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * Modifca el valor del producto al momento de la venta
	 * @param price: nuevo precio del producto
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	/**
	 * Devuelve la cantidad que se compro del producto asociado a la factura
	 * @return Cantidad del producto
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Modifica la cantidad que se compro del producto
	 * @param quantity: nueva cantidad del producto asociada a la factura
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * Clave unica de Bill_Product, esta relacionada cada instancia de esta clase
	 * @return id de Bill_Product
	 */
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Bill_Product [id=" + id + ", product=" + product + ", bill=" + bill + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
}
