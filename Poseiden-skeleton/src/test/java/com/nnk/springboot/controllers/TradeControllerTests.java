package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
@Sql({"/data_test.sql"})
public class TradeControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getTradelist() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/trade/list"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTradeAdd() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/trade/add"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }




    @Test
    public void getTradeValidateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/trade/validate")
                .param("tradeId", "1")
                .param("account", "")
                .param("type", "2")
                .param("buyQuantity", "3"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/add"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTradeUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/trade/update/{id}", "1"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postTradeUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/trade/update/{id}", "1")
                .param("account", "11")
                .param("type", "22")
                .param("buyQuantity", "33"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postTradeUpdateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/trade/update/{id}", "1")
                .param("account", "")
                .param("type", "22")
                .param("buyQuantity", "33"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/update"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void TradeDelete() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/trade/delete/{id}", "2"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getTradeValidateOk() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/trade/validate/")
                .param("tradeId", "1")
                .param("account", "7")
                .param("type", "8")
                .param("buyQuantity", "9"))

                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

}
