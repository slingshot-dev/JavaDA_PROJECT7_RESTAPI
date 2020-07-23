package com.nnk.springboot;


import com.nnk.springboot.domain.BidList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
public class BidListControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Test
    public void getBidListListSecure() throws Exception {
        this.mockMvc.perform(get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("cyrille")
    public void getBidList() throws Exception {
        this.mockMvc.perform(get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @WithUserDetails("cyrille")
    public void getBidListAdd() throws Exception {
        this.mockMvc.perform(get("/bidList/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("cyrille")
    public void getBidListValidateError() throws Exception {
        this.mockMvc.perform(get("/bidList/validate"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("cyrille")
    public void getBidListValidate() throws Exception {
        this.mockMvc.perform(post("/bidList/validate")
        .param("account", "NameTests")
        .param("type", "Desctests")
        .param("bidQuantity", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
