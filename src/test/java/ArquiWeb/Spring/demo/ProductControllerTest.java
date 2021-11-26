package ArquiWeb.Spring.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


import arquiweb.spring.demo.controller.ProductController;
import arquiweb.spring.demo.entities.Product;
import arquiweb.spring.demo.repositories.ProductRepository;
import arquiweb.spring.demo.services.ProductService;


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
		    
		    
		    @Test
		    public void productControllerTestAddProductPOST() throws Exception {
		        
		    	Product prod1 = new Product("Producto 1", 200, (long)150);

		    	
		    	Mockito.when(productService.insert(prod1)).thenReturn(prod1);
		    	
		    	
		    	MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/product")
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
		                .content(this.mapper.writeValueAsString(prod1));

		        mockMvc.perform(mockRequest)
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$", notNullValue()))
		                .andExpect(jsonPath("$.name", is("Producto 1")));
		        
		        }
		    	
		    }

