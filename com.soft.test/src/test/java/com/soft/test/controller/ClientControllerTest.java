package com.soft.test.controller;

import com.soft.test.service.IClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    private IClientService service;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void delete_retorna_204() throws Exception {
        Integer id=9;
        when(service.delete(id)).thenReturn(1);
        mockMvc.perform(delete("/api/clientes/{id}", id))
                .andExpect(status().isNoContent());
        verify(service).delete(id);

    }
}