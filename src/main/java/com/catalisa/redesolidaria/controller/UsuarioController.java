package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PatchMapping(path = "/atualizar/{id}")
    public ResponseEntity<UsuarioDtoResponse> atualizar(@Valid @PathVariable Long id, @RequestBody UsuarioModel usuarioModel){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(usuarioModel,id));

    }
}
