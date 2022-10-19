package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioDtoResponse> alterar(UsuarioModel usuarioModel){
       UsuarioDtoResponse salvar = usuarioService.cadastrar (usuarioModel);
       return ResponseEntity.status (HttpStatus.OK).body (salvar);
    }
}
