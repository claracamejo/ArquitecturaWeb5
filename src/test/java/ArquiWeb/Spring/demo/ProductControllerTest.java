package ArquiWeb.Spring.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import arquiweb.spring.demo.controller.BillController;
import arquiweb.spring.demo.controller.ClientController;
import arquiweb.spring.demo.controller.ProductController;
import arquiweb.spring.demo.entities.Bill;
import arquiweb.spring.demo.entities.Client;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.repositories.BillRepository;
import arquiweb.spring.demo.repositories.ClientRepository;
import arquiweb.spring.demo.repositories.ProductRepository;
import arquiweb.spring.demo.services.BillService;
import arquiweb.spring.demo.services.ClientService;
import arquiweb.spring.demo.services.ProductService;
import springfox.documentation.spring.web.json.Json;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	
			@Autowired
		    ObjectMapper mapper;
			
			@Autowired
		    MockMvc mockMvc;
		    @Mock
		    ProductRepository productRepository;
		    @MockBean
		    ProductService productService;
		    ProductController productController;
		    
		    //Bill factura1 = new Bill(Client client, Date date, Long total);
		    
		    @Test
		    public void productControllerTest() throws Exception {
		        
		    	Product prod1 = new Product("Producto 1", 200, (long)150);
		    	/*
		    	Product prod2 = new Product("Producto 2", 100, (long)250);
		    	Product prod3 = new Product("Producto 3", 400, (long)650);
				*/
			    	
		    	String prodString = "{'name':'Producto 1','stock':200,'price':150}";    	

		    	/*
		    	JSONObject json = new JSONObject();
		    	
		    	json.put("name", "Producto 1");
		    	json.put("stock", 200);
		    	json.put("price", 800);
		    	
		    			    	
		    	Mockito.when(productService.insert(prod1)).thenReturn(true);
		        
		        //mockMvc.perform(post("/product")
		        		//.content("{'id':'1','name':'Producto 1','stock':'200','price':'150'}")		
		        .contentType(MediaType.APPLICATION_JSON)//.param("name","Prod","stock","200"))
		        //.accept(MediaType.APPLICATION_JSON))
	            .content("{'name':'Producto 1','stock':'200','price':'150'}"))
		        //content(asJsonString(new Product("Producto 1", 200, (long)150)))
		        //.andExpect(status().isCreated());
	            //.andDo(print())
	            //.andExpect(status().is2xxSuccessful());
	             *
	             */
		    	Mockito.when(productService.insert(prod1)).thenReturn(true);
		    	RequestBuilder requestBuilder = MockMvcRequestBuilders
		                .post("/product")
		                .accept(MediaType.APPLICATION_JSON)
		                .content(prodString)
		                .contentType(MediaType.APPLICATION_JSON);

		        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		        MockHttpServletResponse response = result.getResponse();
		        
		        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		    	
		    	
		    	
		    	/*
		    	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/product")
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
		                .content(this.mapper.writeValueAsString(prodString));

		        mockMvc.perform(mockRequest)
		                .andExpect(status().isOk());/*
		                .andExpect(jsonPath("$", notNullValue()))
		                .andExpect(jsonPath("$.name", is("John Doe")));
		        */}
		    	
		    }

