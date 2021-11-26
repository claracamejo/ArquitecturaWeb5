package ArquiWeb.Spring.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import arquiweb.spring.demo.controller.BillController;
import arquiweb.spring.demo.controller.ClientController;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.repositories.BillRepository;
import arquiweb.spring.demo.repositories.ClientRepository;
import arquiweb.spring.demo.services.BillService;
import arquiweb.spring.demo.services.ClientService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BillController.class)
public class BillControllerTest {
	
		@Mock
	    ClientRepository clientRepository;
	    @MockBean
		ClientService clientService;
		ClientController controller;
		
		@Autowired
	    MockMvc mockMvc;
	    @Mock
	    BillRepository billRepository;
	    @MockBean
	    BillService billService;
	    BillController billController;
	    
	    /*
	     * GET
	     */
	    @Test
	    public void billControllerTestGetBills() throws Exception {
	        

		    Client RECORD_1 = new Client(1, "Client"+1, "Lastname" +1, "street 00" +1);
		    Client RECORD_2 = new Client(2, "Client"+2, "Lastname" +2, "street 00" +2);
		    Client RECORD_3 = new Client(3, "Client"+3, "Lastname" +3, "street 00" +3);
		    
		    Bill factura1 = new Bill(RECORD_1, Date.valueOf("2021-11-12"), (long)500); 
		    Bill factura2 = new Bill(RECORD_2, Date.valueOf("2021-11-05"), (long)1000);
		    Bill factura3 = new Bill(RECORD_3, Date.valueOf("2021-11-23"), (long)1800);
		    	
	    	    	
	    	List<Bill> records = new ArrayList<>();
	    	records.add(factura1);
	    	records.add(factura2);
	    	records.add(factura3);
	    	
	    	Mockito.when(billService.getBills()).thenReturn(records);
	        
	        mockMvc.perform(get("/bill"))
	                .andExpect(status().isOk())

	                .andExpect(jsonPath("$.[1].client.name", is("Client2")));
	    }
	    

}
