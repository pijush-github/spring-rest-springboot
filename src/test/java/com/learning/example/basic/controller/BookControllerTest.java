package com.learning.example.basic.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.example.basic.dao.BookRepository;
import com.learning.example.basic.entity.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {

	private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository mockRepository;
    
    @BeforeEach
    public void init() {
        Book book = new Book(1L, "A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41"));
        when(mockRepository.findById(1L)).thenReturn(Optional.of(book));
    }
    
    
    @WithMockUser(username = "user")
    @Test
    public void find_authentication_ok() throws Exception {

        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("A Guide to the Bodhisattva Way of Life")))
                .andExpect(jsonPath("$.author", is("Santideva")))
                .andExpect(jsonPath("$.price", is(15.41)));
    }
    
    @Test
    public void find_authentication_401() throws Exception {
        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
    
    @SuppressWarnings("unused")
	private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
