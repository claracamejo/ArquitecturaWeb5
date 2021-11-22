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
*  Este es llamado desde el controlador de Bill_Product y sabe a que Repositorio tiene que llamar 
*  dependiendo la instancia creada de la aplicación.
*
*/


@Service
public class Bill_ProductService {
	@Autowired
	private Bill_productRepository billproduct;
	
	public List<Bill_Product> getClients() {
		return this.billproduct.findAll();
	}
	
	@Transactional
	public boolean insert(Bill_Product b) {
		this.billproduct.save(b);
		return true;
	}
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
	
	@Transactional
	public List<Bill_Product> getByIdBill(int id){
		return this.billproduct.getByIdBill(id);
	}
}
