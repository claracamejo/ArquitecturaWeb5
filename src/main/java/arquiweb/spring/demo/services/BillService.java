package arquiweb.spring.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquiweb.spring.demo.dtos.BillReportDTO;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.repositories.BillRepository;

/**
*  Este servicio es llamado desde el controlador de Bill y decide a que 
*  repositorio llamar dependiendo la instancia creada de la aplicacion
*
*/


@Service
public class BillService {

	@Autowired
	private BillRepository billrepository;
	
	/**
	 * @return
	 * Retorna un listado de Bills
	 */
	public List<Bill> getBills() {
		return this.billrepository.findAll();
	}
	
	/**
	 * @return
	 * Retorna un listado de BillReportDTO
	 */
	public List<BillReportDTO> report(){
		return this.billrepository.salesReport();
	}
}
