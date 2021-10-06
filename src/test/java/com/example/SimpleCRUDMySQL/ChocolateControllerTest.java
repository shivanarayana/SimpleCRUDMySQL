package com.example.SimpleCRUDMySQL;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ChocolateController.class)
@WithMockUser
public class ChocolateControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChocolateService chocoServ;

    @MockBean
    private ChocolateRepository chocoRepo;

    ChocolateDAO mockChoco = new ChocolateDAO("Bradbury", 10L, 100L,100L);
    ChocolateDAO mockChocoAdd = new ChocolateDAO("Bradbury2", 12L, 102L,102L);

    String output = "{\"name\":\"Bradbury\",\"price\":\"10\",\"weight\":\"100\",\"quantity\":\"100\"}";

    @Test
    public void createChocolate() throws Exception {
        Mockito.when(chocoServ.addChocolateDB(Mockito.any(ChocolateDAO.class))).thenReturn("Chocolate Added");
        // Send chocolateDao as body to /add
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add").accept(MediaType.APPLICATION_JSON)
                .content(output).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getChocolate() throws Exception {
        Mockito.when(chocoServ.getChocolateDB(Mockito.anyString())).thenReturn(java.util.Optional.of(mockChoco));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get/Bradbury").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "{name:Bradbury,price:10,weight:100,quantity:100}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getAllChocolate() throws Exception {
        List<ChocolateDAO> mockChocoList = new ArrayList<ChocolateDAO>();
        mockChocoList.add(mockChoco);
        mockChocoList.add(mockChocoAdd);
        String expected = "[{name:Bradbury,price:10,weight:100,quantity:100},{name:Bradbury2,price:12,weight:102,quantity:102}]";

        Mockito.when(chocoServ.getAllChocolateDB()).thenReturn(expected);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

//
//    @Test
//    public void putChocolate() {
//    }
//
//    @Test
//    public void deleteChocolate() {
//    }
}