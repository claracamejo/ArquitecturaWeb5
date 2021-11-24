package arquiweb.spring.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquiweb.spring.demo.dtos.BillReportDTO;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.entities.Bill_Product;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.repositories.Bill_productRepository;

/**
*  Este servicio es llamado desde el controlador de Bill_Product y decide a que 
*  repositorio llamar dependiendo la instancia creada de la aplicacion
*
*/


@Service
public class Bill_ProductService {
	@Autowired
	private Bill_productRepository billproduct;
	
	/**
	 * @return List<Bill_Product> 
	 * Retorna una lista de Bill_product 
	 */
	public List<Bill_Product> getClients() {
		return this.billproduct.findAll();
	}
	
	/**
	 * @param b
	 * @return 
	 * Retorna true en el caso de que el Objeto Bill_Product sea guardado con exito
	 */
	@Transactional
	public boolean insert(Bill_Product b) {
		this.billproduct.save(b);
		return true;
	}
	
	/**
	 * @param dni
	 * @return
	 * Retorna true si el objeto Bill_product con ese dni fue borrado con exito
	 */
	@Transactional
	public boolean delete(int dni) {
		this.billproduct.deleteById(dni);
		return true;
	}
	//@Transactional
	/*
	public boolean update(Product product, Bill bill, long price, int quantity, int id) {
		this.billproduct.updateBill_Product(product, bill, price,quantity, id);
		return true;
	}*/
	
	/**
	 * @param id
	 * @return
	 * Retorna una lista de Bill_Product que esten asociadas con el id de Bill correspondiente
	 */
	@Transactional
	public List<Bill_Product> getByIdBill(int id){
		return this.billproduct.getByIdBill(id);
	}
}
