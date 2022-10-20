package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import com.catalisa.redesolidaria.repository.TipoDaDeficienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TipoDaDeficienciaService {
    @Autowired
    TipoDaDeficienciaRepository tipoDaDeficienciaRepository;

    public TipoDaDeficienciaModel cadastrarTipoDaDeficiencia(TipoDaDeficienciaModel tipoDaDeficienciaModel) {
        return tipoDaDeficienciaRepository.save(tipoDaDeficienciaModel);
    }


    public TipoDaDeficienciaModel atualizar(Long id, TipoDaDeficienciaModel tipoDaDeficienciaModel) {
        tipoDaDeficienciaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + id));

        return tipoDaDeficienciaRepository.save(tipoDaDeficienciaModel);

    }
}
