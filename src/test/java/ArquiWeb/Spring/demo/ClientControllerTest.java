package ArquiWeb.Spring.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import arquiweb.spring.demo.controller.ClientController;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.repositories.ClientRepository;
import arquiweb.spring.demo.services.ClientService;



@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

	@Autowired
    MockMvc mockMvc;
    @Mock
    ClientRepository clientRepository;
    @MockBean
	ClientService clientService;
	ClientController controller;
    
    
	/*
     * GET
     */    
    @Test
    public void clientControllerTestGetAll() throws Exception {
    	
    	Client RECORD_1 = new Client(1, "Client"+1, "Lastname" +1, "street 00" +1);
        Client RECORD_2 = new Client(2, "Client"+2, "Lastname" +2, "street 00" +2);
        Client RECORD_3 = new Client(3, "Client"+3, "Lastname" +3, "street 00" +3);
        
    	List<Client> records = new ArrayList<>();
    	records.add(RECORD_1);
    	records.add(RECORD_2);
    	records.add(RECORD_3);
    	
    	Mockito.when(clientService.getClients()).thenReturn(records);
        
        mockMvc.perform(get("/client"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.[1].name", is("Client2")));
    }
    
    /*
     * GET
     */
    @Test
    public void clientControllerTestGetById() throws Exception {
        
    	Optional<Client> cli = Optional.ofNullable(new Client(4, "Client"+4, "Lastname" +4, "street 00" +4));
    	
    	
    	
    	Mockito.when(clientService.getClient(4)).thenReturn(cli);
        
        mockMvc.perform(get("/client/4"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name", is("Client4")));
    }
    
    /*
     * DELETE
     */
    @Test
    public void clientControllerTestDeleteById() throws Exception {
        
    	
    	Mockito.when(clientService.delete(2)).thenReturn(true);
        
        mockMvc.perform(delete("/client/2").contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());
               
    }
	
}
