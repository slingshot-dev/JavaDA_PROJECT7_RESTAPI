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
public class CurvePointControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getCurvePointlist() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/curvePoint/list"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCurvePointAdd() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/curvePoint/add"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getCurvePointValidateOk() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/curvePoint/validate")
                .param("id", "1")
                .param("curveId", "1")
                .param("term", "2")
                .param("value", "3"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getCurvePointValidateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", "")
                .param("term", "2")
                .param("value", "3"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/add"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCurvePointUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/curvePoint/update/{id}", "1"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postCurvePointUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/curvePoint/update/{id}", "1")
                .param("curveId", "11")
                .param("term", "22")
                .param("value", "33"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postCurvePointUpdateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/curvePoint/update/{id}", "1")
                .param("curveId", "11")
                .param("term", "22")
                .param("value", ""))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void CurvePointDelete() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/curvePoint/delete/{id}", "2"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }


}
