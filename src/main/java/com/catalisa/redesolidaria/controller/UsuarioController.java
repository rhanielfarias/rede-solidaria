package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
@Autowired
    private UsuarioService usuarioService;


    @PostMapping(path = "/usuario/create")
    public ResponseEntity<UsuarioDtoResponse> cadastrarUsuario(@RequestBody UsuarioModel usuarioModel) {
        UsuarioDtoResponse usuario = usuarioService.cadastrar(usuarioModel);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
}
