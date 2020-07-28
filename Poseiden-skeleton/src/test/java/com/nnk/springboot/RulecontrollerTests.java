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
public class RulecontrollerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getRulelist() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/ruleName/list"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRuleAdd() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/ruleName/add"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getRuleValidateOk() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/ruleName/validate")
                .param("name", "1")
                .param("description", "2")
                .param("json", "3")
                .param("template", "4")
                .param("sqlStr", "5")
                .param("sqlPart", "6"))

                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getRuleValidateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/ruleName/validate")
                .param("name", "")
                .param("description", "2")
                .param("json", "3")
                .param("template", "4")
                .param("sqlStr", "5")
                .param("sqlPart", "6"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/add"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRuleUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/ruleName/update/{id}", "1"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postRuleUpdate() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/ruleName/update/{id}", "1")
                .param("name", "11")
                .param("description", "22")
                .param("json", "33")
                .param("template", "44")
                .param("sqlStr", "55")
                .param("sqlPart", "66"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Disabled
    public void postRuleUpdateKo() throws Exception {
        // Arange & Act
        this.mockMvc.perform(post("/ruleName/update/{id}", "1")
                .param("name", "")
                .param("description", "22")
                .param("json", "33")
                .param("template", "44")
                .param("sqlStr", "55")
                .param("sqlPart", "66"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void RuleDelete() throws Exception {
        // Arange & Act
        this.mockMvc.perform(get("/ruleName/delete/{id}", "2"))
                // Assert
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

}
