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
public class BidListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getBidList() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/bidList/list"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getBidListAdd() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/bidList/add"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getBidListValidateOk() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/bidList/validate")
                .param("bidListId", "1")
                .param("account", "NameTests")
                .param("type", "Desctests")
                .param("bidQuantity", "10"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getBidListValidateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/bidList/validate")
                .param("account", "")
                .param("type", "Desctests")
                .param("bidQuantity", "10"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/add"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getBidListUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/bidList/update/{id}", "1"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postBidListUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/bidList/update/{id}", "1")
                .param("account", "NameTestsUpdate")
                .param("type", "DescTestsUpdate")
                .param("bidQuantity", "50"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postBidListUpdateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/bidList/update/{id}", "1")
                .param("account", "")
                .param("type", "DescTestsUpdate")
                .param("bidQuantity", "50"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void BidListDelete() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/bidList/delete/{id}", "2"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

}
