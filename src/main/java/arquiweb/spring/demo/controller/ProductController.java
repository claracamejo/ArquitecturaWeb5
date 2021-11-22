package arquiweb.spring.demo.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arquiweb.spring.demo.dtos.ProductReportDTO;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.services.ProductService;

import org.slf4j.Logger;

/*
*  Dado un pedido REST, el controlador de Product atiende el pedido y llama al servicio requerido.
*  Objetivo principal mapear las URL para acceder al recurso necesario.
*/


@RestController
@RequestMapping("/product")
class ProductController {
private static Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public List<Product> getAll() {
		return this.productService.getProducts();
	}
	
	@PostMapping("")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		boolean ok = this.productService.insert(p);
		if(!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		boolean ok = this.productService.delete(id);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable( "id" ) int id, @RequestBody Product product) {
		boolean ok = false;
		if(product != null) {
			ok = this.productService.update(product.getName(),product.getStock(), product.getPrice(), id);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getReport")
	public List<ProductReportDTO> getProductReport(){
		return this.productService.getReport();
	}
}
