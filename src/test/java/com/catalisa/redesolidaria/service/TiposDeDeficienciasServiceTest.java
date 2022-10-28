package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import com.catalisa.redesolidaria.repository.TipoDaDeficienciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TiposDeDeficienciasServiceTest {
    @Mock
    TipoDaDeficienciaService tipoDaDeficienciaService;

    @MockBean
    TipoDaDeficienciaRepository tipoDaDeficienciaRepository;

    private TipoDaDeficienciaModel tipoDaDeficienciaModel;

    @BeforeEach
    private void inicializador() {
        MockitoAnnotations.openMocks(this);
        tipoDaDeficienciaModel = new TipoDaDeficienciaModel();
    }

    @Test
    @DisplayName("Verificando se o cadastro do tipo da deficiência está funcionando")
    void verificandoSeOCadastroDoTipoDaDeficienciaEstaFuncionando() {
        tipoDaDeficienciaRepository.save(tipoDaDeficienciaModel);
        Mockito.when(tipoDaDeficienciaRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.verify(tipoDaDeficienciaRepository, Mockito.times(1)).save(tipoDaDeficienciaModel);
    }


}