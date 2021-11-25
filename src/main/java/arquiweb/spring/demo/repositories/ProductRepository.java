package arquiweb.spring.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import arquiweb.spring.demo.dtos.ProductReportDTO;
import arquiweb.spring.demo.entities.Product;
/**
 * Repositorio de Product, las operaciones que se declaren en esta interfaz son las que estan
 * relacionadas con las consultas a la base de datos de la tabla de Product
 */


public interface ProductRepository extends JpaRepository<Product, Object> {

	/**
	 * Modificar un Product seteando los datos que le fueron pasados por parametro
	 * @param name: nuevo nombre del producto
	 * @param stock: nueva cantidad disponible del producto
	 * @param price: nuevo precio por el que se va a vender el producto
	 * @param id: Primarykey del producto que se quiere actualizar
	 */
	@Modifying
	@Query("UPDATE Product SET name = :name, stock = :stock, price =:price WHERE id = :id")
	public void updateProduct(@Param("name") String name, @Param("stock") int stock,@Param("price") long price, @Param("id") int id);

	/**
	 * Devuelve el producto mas vendido
	 * @return Lista de productos con el formato de la clase @ProductReportDTO
	 */
	@Query(name = "product_report_dto", nativeQuery = true)
	public List<ProductReportDTO> productSalesReport();
}
