package com.example.SimpleCRUDMySQL;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ChocolateService.class)
@WithMockUser
class ChocolateServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChocolateService chocoServ;

    @MockBean
    private ChocolateRepository chocoRepo;

    ChocolateDAO mockChocoNull = null;
    ChocolateDAO mockChoco = new ChocolateDAO("Bradbury", 10L, 100L,100L);
    ChocolateDAO mockChocoAdd = new ChocolateDAO("Bradbury2", 12L, 102L,102L);

    String output = "{\"name\":\"Bradbury\",\"price\":\"10\",\"weight\":\"100\",\"quantity\":\"100\"}";

    @Test
    public void addChocolateDB() {
        List<ChocolateDAO> mockChocoList = new ArrayList<ChocolateDAO>();
        mockChocoList.add(mockChoco);

        Mockito.when(chocoRepo.findAll()).thenReturn(mockChocoList);

       // Mockito.when(chocoRepo.save(mockChocoAdd)).thenReturn("Chocolate Added");

        assertEquals("Bradbury",mockChocoList.get(0).getName());
       // Mockito.when(chocoRepo..addChocolateDB(Mockito.any(ChocolateDAO.class))).thenReturn("Chocolate Added");
    }

    @Test
    public void getChocolateDBFound() throws JSONException {
        Mockito.when(chocoRepo.findById(Mockito.anyString())).thenReturn(Optional.of(mockChoco));
        String expected = "{name:Bradbury, price:10, weight:100, quantity:100}";
        assertEquals(expected, mockChoco.toString());
    }

    @Test
    public void getChocolateDBNotFound() throws JSONException {
        Mockito.when(chocoRepo.findById(Mockito.anyString())).thenReturn(null);
        String expected = null;
        assertNotEquals(expected, mockChoco.toString());
    }

    @Test
    public void getAllChocolateDB() {
        List<ChocolateDAO> mockChocoList = new ArrayList<ChocolateDAO>();
        mockChocoList.add(mockChoco);
        mockChocoList.add(mockChocoAdd);
        String expected = "[{name:Bradbury, price:10, weight:100, quantity:100}," +
                " {name:Bradbury2, price:12, weight:102, quantity:102}]";

        Mockito.when(chocoRepo.findAll()).thenReturn(mockChocoList);
        assertEquals(expected, mockChocoList.toString());
    }

//    @Test
//    public void changedChocolateDB() {
//    }

    @Test
    public void deleteChocolateDB() {
        ChocolateDAO product = new ChocolateDAO("DeleteChoco",5L,4L,16L);
        chocoRepo.save(product);
        chocoRepo.delete(product);
        Optional optional = chocoRepo.findById(product.getName());
        assertEquals(Optional.empty(), optional);
    }
}