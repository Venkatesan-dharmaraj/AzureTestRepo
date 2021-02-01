package com.example.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.EcartApplication;
import com.example.entity.Products;
import com.example.repository.ProductsRepository;
import com.example.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = EcartApplication.class)
public class ProductControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@MockBean
	ProductService prodServ;
	
	@MockBean
	ProductsRepository prodRepo;
	
	 @Autowired
	 private ObjectMapper objectMapper;
	 

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        prodServ = mock(ProductService.class);
        prodRepo = mock(ProductsRepository.class);
	}
	
	@Test
	public void checkProductController() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/check").accept(MediaType.APPLICATION_JSON))
		.andReturn();
		 int status = mvcResult.getResponse().getStatus();
		 System.out.println(status);
		 assertEquals(201, status);
		 String content = mvcResult.getResponse().getContentAsString();
		 assertEquals("hi Client", content);
	}
	
	@Test
	public void saveProductsTest()throws Exception {
		Products prod = new Products();
		prod.setProductID(1);
		prod.setProductName("TestProd");
		prod.setDescription("For Testing Purpose");
		prod.setPrice(BigDecimal.valueOf(12.2));
		prod.setSize("Medium");
		 
		MvcResult result = this.mockMvc.perform(post("/saveProduct")
                 .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(prod).trim()))
				 .andExpect(status().isCreated())
				 .andDo(print()).
				 andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		System.out.println("content " +content);
		
	}
	
	
	/*
	 * @Test
	 * 
	 * @ExceptionHandler(Exception.class) public void showAllProductsTest() throws
	 * Exception {
	 * 
	 * this.mockMvc.perform(get("/showAllProducts").contentType(MediaType.
	 * APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON)).andDo(print())
	 * .andExpect(status().isOk()).andReturn();
	 * 
	 * 
	 * }
	 * 
	 */
	
	
	
	

}
