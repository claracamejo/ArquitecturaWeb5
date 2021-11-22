package arquiweb.spring.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquiweb.spring.demo.dtos.BillReportDTO;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.repositories.BillRepository;

/**
*  Este es llamado desde el controlador de Bill y sabe a que Repositorio tiene que llamar 
*  dependiendo la instancia creada de la aplicación.
*
*/


@Service
public class BillService {

	@Autowired
	private BillRepository billrepository;
	
	public List<Bill> getBills() {
		return this.billrepository.findAll();
	}
	
	public List<BillReportDTO> report(){
		return this.billrepository.salesReport();
	}
}
