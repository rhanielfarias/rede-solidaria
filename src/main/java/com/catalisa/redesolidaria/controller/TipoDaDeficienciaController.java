package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import com.catalisa.redesolidaria.service.TipoDaDeficienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class TipoDaDeficienciaController {
    @Autowired
    TipoDaDeficienciaService tipoDaDeficienciaService;

    @PostMapping(path = "/tipodadeficiencia/create")
    public ResponseEntity<TipoDaDeficienciaModel> cadastrarTipoDaDeficiencia(@RequestBody TipoDaDeficienciaModel tipoDaDeficienciaModel) {
        TipoDaDeficienciaModel tipoDaDeficienciaModel1 = tipoDaDeficienciaService.cadastrarTipoDaDeficiencia(tipoDaDeficienciaModel);
        return new ResponseEntity<>(tipoDaDeficienciaModel1, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/atualizar/{id}")
    public TipoDaDeficienciaModel atualizar(@PathVariable Long id, @RequestBody TipoDaDeficienciaModel tipoDaDeficienciaModel) {
        return tipoDaDeficienciaService.atualizar(id, tipoDaDeficienciaModel);
    }
}
