package arquiweb.spring.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arquiweb.spring.demo.dtos.BillReportDTO;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.services.BillService;

/*
*  Dado un pedido REST, el controlador de Bill atiende el pedido y llama al servicio requerido.
*  Objetivo principal mapear las URL para acceder al recurso necesario.
*/


@RestController
@RequestMapping("/bill")
public class BillController {
	private static Logger LOG = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private BillService billService;
	
	@GetMapping("")
	public List<Bill> getAll() {
		return this.billService.getBills();
	}
	
	@GetMapping("/report")
	public List<BillReportDTO> report(){
		return this.billService.report();
	}
}
