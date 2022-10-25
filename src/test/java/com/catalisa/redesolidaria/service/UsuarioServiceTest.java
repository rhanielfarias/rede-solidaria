package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsuarioServiceTest {
    @Mock
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    private UsuarioModel usuarioModel;

    @BeforeEach
    private void inicializador() {
        MockitoAnnotations.openMocks(this);
        usuarioModel = new UsuarioModel();
    }

    @Test
    @DisplayName("Testando o cadastro do usuário")
    void verificandoSeEstaSendoEfetuadoOCadastroDoUsuario() {
        usuarioRepository.save(usuarioModel);
        Mockito.when(usuarioRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuarioModel);
    }

    @Test
    void buscarCadastros() {
        List<UsuarioModel> usuarioModel = new ArrayList<> ();
        Mockito.when(usuarioRepository.findAll()).thenReturn((List<UsuarioModel>) usuarioModel);
        List<UsuarioDtoResponse> listaDeUsuarios = usuarioService.buscar ();
        Assertions.assertEquals(listaDeUsuarios,usuarioModel);
    }
}
