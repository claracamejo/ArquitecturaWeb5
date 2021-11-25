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

/**
*  Dado un pedido REST, el controlador de Product atiende el pedido y llama al servicio requerido.
*  Objetivo principal mapear las URL para acceder al recurso necesario.
*/


@RestController
@RequestMapping("/product")
public class ProductController {
private static Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Devuelve una lista de todos los Productos en la base de datos
	 * @return Lista de Producto
	 */
	@GetMapping("")
	public List<Product> getAll() {
		return this.productService.getProducts();
	}
	
	/**
	 * crea un nuevo producto en la base de datos
	 * @param p: objeto producto
	 * @return Http status resultado de como haya salido la operacion
	 */
	@PostMapping("")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		boolean ok = this.productService.insert(p);
		if(!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}
	
	/**
	 * Elimina un producto de la base de datos
	 * @param id: clave primaria del producto que se quiere eliminar
	 * @return boolean: true si fue eliminado con exito, false si no se puedo eliminar
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		boolean ok = this.productService.delete(id);
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	/**
	 * Actualiza los datos de un Producto en la Base de Datos
	 * @param id: clave primaria del producto que se quiere modificar
	 * @param product: objeto producto con los datos actualizados
	 * @return boolean: true si fue actualizado con exito, false si no se puedo actualizar
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable( "id" ) int id, @RequestBody Product product) {
		boolean ok = false;
		if(product != null) {
			ok = this.productService.update(product.getName(),product.getStock(), product.getPrice(), id);
		}
		if(!ok) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	/**
	 * Devuelve un reporte de producto
	 * @return listado de producto con el formato de la clase ProductoReportDTO
	 */
	@GetMapping(value = "/getReport")
	public List<ProductReportDTO> getProductReport(){
		return this.productService.getReport();
	}
}
