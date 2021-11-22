package arquiweb.spring.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import arquiweb.spring.demo.dtos.BillReportDTO;
import arquiweb.spring.demo.entities.Bill;
 /**
  * Repositorio de Factura (Bill), las operaciones
  * que se declaren en esta interfaz son las que estan.
  * relacionadas con operaciones en la base de datos
  * 
  * Consideramos que factura no se puede actualizar
  * por lo que no tiene un update, como si tendria cliente o producto  
  */
public interface BillRepository extends JpaRepository<Bill, Object> {
	
	/*
	 * Devuelve las ventas por dia
	 */
	
	@Query(name = "bill_report_for_day_dto", nativeQuery = true)
	public List<BillReportDTO> salesReport();

}
