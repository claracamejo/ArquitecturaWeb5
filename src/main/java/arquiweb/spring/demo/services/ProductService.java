package arquiweb.spring.demo.services;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquiweb.spring.demo.dtos.ProductReportDTO;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.repositories.ProductRepository;

/**
*   Este servicio es llamado desde el controlador de Product y decide a que 
*   repositorio llamar dependiendo la instancia creada de la aplicacion
*
*/
@Service
public class ProductService {
	@Autowired
	private ProductRepository products;
	
	/**
	 * @return
	 * Retorna un listado de productos
	 */
	public List<Product> getProducts() {
		return this.products.findAll();
	}
	
	/**
	 * @param p
	 * @return
	 * Retorna true si el Producto fue insertado con exito
	 */
	@Transactional
	public boolean insert(Product p) {
		this.products.save(p);
		return true;
	}
	
	/**
	 * @param name
	 * @param stock
	 * @param price
	 * @param id
	 * @return
	 * Retorna true si el Producto fue actualizado con exito
	 */
	@Transactional
	public boolean update(String name, int stock, long price, int id) {
		this.products.updateProduct(name, stock, price, id);
		return true;
	}
	
	/**
	 * @param id
	 * @return
	 * Retorna true si el Producto fue eliminado con exito
	 */
	@Transactional
	public boolean delete(int id) {
		this.products.deleteById(id);
		return true;
	}
	
	/**
	 * @return
	 * Retorna el listado del reporte de Productos
	 */
	public List<ProductReportDTO> getReport(){
		return this.products.productSalesReport();
	}

}
