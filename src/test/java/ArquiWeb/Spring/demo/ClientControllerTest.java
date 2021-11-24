package ArquiWeb.Spring.demo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import arquiweb.spring.demo.ArquiWebTp4Application;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ArquiWebTp4Application.class
)
public class ClientControllerTest {
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	public void testHelloEndpointIsOK() throws Exception {
        ResultActions variable = this.mockMvc.perform(get("/client/2"));
        variable.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Client2"));
       
        
/*        mockMvc.perform(get("/person/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.person.name").value("Jason"));
*/
        
        
    /*    
            variable.andExpect(status().isOk())
            .andExpect(content().string("Hola Mundo"));*/
	}
}
