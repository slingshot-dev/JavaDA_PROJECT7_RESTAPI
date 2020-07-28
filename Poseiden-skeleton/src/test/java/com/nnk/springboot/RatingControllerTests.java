package com.nnk.springboot;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
@Sql({"/data_test.sql"})
public class RatingControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getRatinglist() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/rating/list"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRatingAdd() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/rating/add"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getRatingValidateOk() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/rating/validate")
                .param("MoodysRating", "1")
                .param("SandPRating", "2")
                .param("FitchRating", "3")
                .param("orderNumber", "4"))

        // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getRatingValidateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/rating/validate")
                .param("MoodysRating", "")
                .param("SandPRating", "2")
                .param("FitchRating", "3")
                .param("orderNumber", "4"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/add"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRatingUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/rating/update/{id}", "1"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postRatingUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/rating/update/{id}", "1")
                .param("MoodysRating", "11")
                .param("SandPRating", "22")
                .param("FitchRating", "33")
                .param("orderNumber", "44"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Disabled
    public void postRatingUpdateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/rating/update/{id}", "1")
                .param("MoodysRating", "")
                .param("SandPRating", "22")
                .param("FitchRating", "33")
                .param("orderNumber", "44"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/update"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void RatingDelete() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/rating/delete/{id}", "2"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
}
