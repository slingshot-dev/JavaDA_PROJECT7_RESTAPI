package com.nnk.springboot.controllers;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
@Sql({"/data_test.sql"})
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getUserlist() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/user/list"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserAdd() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/user/add"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getUserValidateOk() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/user/validate")
                .param("fullname", "1")
                .param("username", "2")
                .param("password", "Rsv1000R!")
                .param("role", "USER"))


                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getUserValidateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/user/validate")
                .param("fullname", "")
                .param("username", "2")
                .param("password", "$2a$10$ErEE83YRmdtAGvndMDVZje0jah2IDUF9rR5Y1rZkIa6pVB3mBB0XK")
                .param("role", "USER"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/add"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/user/update/{id}", "2"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postUserUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/user/update/{id}", "2")
                .param("fullname", "11")
                .param("username", "22")
                .param("password", "$2a$10$ErEE83YRmdtAGvndMDVZje0jah2IDUF9rR5Y1rZkIa6pVB3mBB0XK")
                .param("role", "USER"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postUserUpdateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/user/update/{id}", "2")
                .param("fullname", "")
                .param("username", "2")
                .param("password", "Rsv1000R!")
                .param("role", "USER"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/update"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void UserDelete() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/user/delete/{id}", "3"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }




}
