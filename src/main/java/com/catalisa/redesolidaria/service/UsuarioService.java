package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDtoResponse cadastrar(UsuarioModel usuarioModel) {

        usuarioRepository.save(usuarioModel);

        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(usuarioModel.getId()
                ,usuarioModel.getCategoria(),usuarioModel.getNome(),
                usuarioModel.getEmail(),usuarioModel.getLatitude(),
                usuarioModel.getLongitude());

        return usuarioDtoResponse;

    }

    private UsuarioDtoResponse atualizar(UsuarioModel usuarioModel, Long id){

        usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + id));
        usuarioRepository.save(usuarioModel);

        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(usuarioModel.getId()
                ,usuarioModel.getCategoria(),usuarioModel.getNome(),
                usuarioModel.getEmail(),usuarioModel.getLatitude(),
                usuarioModel.getLongitude());

        return usuarioDtoResponse;

    }
}
