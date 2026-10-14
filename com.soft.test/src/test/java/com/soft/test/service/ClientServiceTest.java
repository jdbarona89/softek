package com.soft.test.service;

import com.soft.test.commun.exception.IdDeClienteNoValidoExceptio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private IClientService repository;

    @InjectMocks
    private ClientService service;



    @Test
    void delete() {

        Integer id=1;
        when(repository.delete(1)).thenReturn(1);
        Integer resultado=service.delete(1);
        assertEquals(resultado,1);
        verify(repository).delete(1);

    }

    @Test
    void cliente_no_existe(){
        Integer id=null;
        assertThrows(IdDeClienteNoValidoExceptio.class,()->service.delete(id));
        verifyNoInteractions(repository.delete(id));
    }
}