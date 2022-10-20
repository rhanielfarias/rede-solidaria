package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import com.catalisa.redesolidaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping(path = "/create")
    public ResponseEntity<UsuarioDtoResponse> cadastrarUsuario(@RequestBody UsuarioModel usuarioModel) throws Exception {
        UsuarioDtoResponse usuario = usuarioService.cadastrar(usuarioModel);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/atualizar/{id}")
    public ResponseEntity<UsuarioDtoResponse> atualizar(@Valid @PathVariable Long id, @RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(usuarioModel, id));

    }

    @GetMapping
    public ResponseEntity<List< UsuarioModel>> buscar(){
       return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());

    }
}
