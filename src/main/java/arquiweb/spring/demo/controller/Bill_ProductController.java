package arquiweb.spring.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arquiweb.spring.demo.entities.Bill_Product;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.services.Bill_ProductService;


/*
*  Dado un pedido REST, el controlador de Bill_Product atiende el pedido y llama al servicio requerido.
*  Objetivo principal mapear las URL para acceder al recurso necesario.
*/

@RestController
@RequestMapping("/billproduct")
class Bill_ProductController {
	private static Logger LOG = LoggerFactory.getLogger(Bill_ProductController.class);
	
	@Autowired
	private Bill_ProductService billProductService;
	
	@GetMapping("")
	public List<Bill_Product> getAll() {
		return this.billProductService.getClients();
	}
	
	@GetMapping(value = "/bill/{id}")
	public List<Bill_Product> getByIdBill(@PathVariable( "id" ) int id) {
		return this.billProductService.getByIdBill(id);
	}
	
}
