package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import com.catalisa.redesolidaria.service.TipoDaDeficienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TipoDaDeficienciaController {
    @Autowired
    TipoDaDeficienciaService tipoDaDeficienciaService;

    @PatchMapping(path = "/atualizar/{id}")
    public TipoDaDeficienciaModel atualizar(@PathVariable Long id, @RequestBody TipoDaDeficienciaModel tipoDaDeficienciaModel){
        return tipoDaDeficienciaService.atualizar(id,tipoDaDeficienciaModel);
    }
}
