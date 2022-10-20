package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    UsuarioModel usuarioModel;

    public boolean validadorDeMenorDeIdade(UsuarioModel usuarioModel) {
        LocalDate data = usuarioModel.getDataDeNascimento().plusYears(18);
        LocalDate now = LocalDate.now();
        return data.isBefore(now);
    }

    public UsuarioDtoResponse cadastrar(UsuarioModel usuarioModel) throws Exception {


        Boolean validandoIdade = validadorDeMenorDeIdade(usuarioModel);
        if (validandoIdade) {
            usuarioRepository.save(usuarioModel);
            UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(usuarioModel.getId()
                    , usuarioModel.getCategoria(), usuarioModel.getTipoDaDeficiencia(), usuarioModel.getNome(),
                    usuarioModel.getEmail(), usuarioModel.getLatitude(),
                    usuarioModel.getLongitude());

            return usuarioDtoResponse;
        } else {
            return null;
        }
    }

    public UsuarioDtoResponse atualizar(UsuarioModel usuarioModel, Long id) {
        usuarioModel.setTipoDaDeficiencia(null);

        usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + id));
        usuarioRepository.save(usuarioModel);

        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(usuarioModel.getId()
                , usuarioModel.getCategoria(), usuarioModel.getTipoDaDeficiencia(), usuarioModel.getNome(),
                usuarioModel.getEmail(), usuarioModel.getLatitude(),
                usuarioModel.getLongitude());

        return usuarioDtoResponse;

    }
}
