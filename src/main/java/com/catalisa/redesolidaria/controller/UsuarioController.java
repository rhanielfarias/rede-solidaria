package com.catalisa.redesolidaria.controller;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoLogin;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoSolicitacao;
import com.catalisa.redesolidaria.model.dto.VoluntarioDtoId;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import com.catalisa.redesolidaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/voluntarios")
    public ResponseEntity<List<UsuarioDtoResponse>> buscarVoluntarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarVoluntarios());

    }

    @GetMapping
    public ResponseEntity<List<UsuarioDtoResponse>> buscarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDtoResponse> buscaUsuarioPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarID(id));
    }


    @PostMapping(path = "/create")
    public ResponseEntity<UsuarioDtoResponse> cadastrarUsuario(@RequestBody @Valid UsuarioModel usuarioModel) throws Exception {
        UsuarioDtoResponse usuario = usuarioService.cadastrar(usuarioModel);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/atualizar/{id}")
    public ResponseEntity<UsuarioDtoResponse> atualizar(@Valid @PathVariable Long id, @RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(usuarioModel, id));
    }

    @GetMapping(path = "/solicitacao/{id}")
    public ResponseEntity<UsuarioDtoSolicitacao> solicitacaoAjuda(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.solicitarAjuda(id));
    }

    @DeleteMapping(path = "/{id}")
    public String deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return "Usu??rio deletado com sucesso!";
    }

    @GetMapping(path = "/voluntario")
    public ResponseEntity<List<VoluntarioDtoId>> buscaPorVoluntariosId(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarVoluntarioId(id));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UsuarioDtoLogin> loginUsuario(@RequestBody UsuarioModel usuarioModel) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.loginUser(usuarioModel));
    }

}
