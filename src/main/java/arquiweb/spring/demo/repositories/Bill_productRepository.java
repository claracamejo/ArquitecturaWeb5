package arquiweb.spring.demo.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import arquiweb.spring.demo.dtos.BillReportDTO;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.entities.Bill_Product;
import arquiweb.spring.demo.entities.Product;

/**
 * Repositorio de Bill_Product, las operaciones que se declaren en esta interfaz son las que estan
 * relacionadas con las consultas a la base de datos de la tabla de Bill_Product
 */



public interface Bill_productRepository extends JpaRepository<Bill_Product, Object> {

	
	/**
	 * Modificar un Bill_Product seteando los datos que le fueron pasados por parametro
	 * @param product: nuevo producto,
	 * @param bill: nueva factura que se quiere relacionar
	 * @param price: precio del producto
	 * @param quantity: nueva cantidad del nuevo producto
	 * @param id: Primarykey de Bill_Product que se quiere modificar
	 */
	@Modifying
	@Query(value="UPDATE Bill_Product SET product = :product, bill = :bill, price = :price, quantity = :quantity WHERE id = :id", nativeQuery = true)
	public void updateBill_Product(@Param("product") Product product, @Param("bill") Bill bill, 
								  @Param("price") long price, @Param("quantity") int quantity, @Param("id") int id);
	
	/**
	 * Selecciona todas las Bill_Product que tengan el id de factura pasado por parametro.
	 * @param id_bill: id de la Factura que se quiere buscar
	 * @return Lista de productos que tienen el id de la factura buscado
	 */
	@Modifying
	@Query(value="SELECT * FROM Bill_Product WHERE id_bill = :id_bill", nativeQuery = true)
	public List<Bill_Product>  getByIdBill(@Param("id_bill") int id_bill);
	
}
