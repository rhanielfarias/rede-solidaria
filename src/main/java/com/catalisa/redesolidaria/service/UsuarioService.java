package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDtoResponse> buscar() {
        List<UsuarioModel> buscarUsuario = usuarioRepository.findAll();
        return buscarUsuario.stream().map(usuario -> new UsuarioDtoResponse(usuario.getId(),
                usuario.getCategoria(), usuario.getTipoDaDeficiencia(), usuario.getNome(), usuario.getEmail(), usuario.getLatitude(),
                usuario.getLongitude())).collect(Collectors.toList());
    }

    public List<UsuarioDtoResponse> buscarID(Long id) {
        Optional<UsuarioModel> usuario = Optional.of(usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id não encontrado" + id)));
        return usuario.stream().map(usuarioModel -> new UsuarioDtoResponse(usuario.get().getId(),
                usuario.get().getCategoria(), usuario.get().getTipoDaDeficiencia(), usuario.get().getNome(), usuario.get().getEmail(),
                usuario.get().getLatitude(), usuario.get().getLongitude())).collect(Collectors.toList());
    }

    public boolean validadorDeMenorDeIdade(UsuarioModel usuarioModel) {
        LocalDate data = usuarioModel.getDataDeNascimento().plusYears(18);
        LocalDate now = LocalDate.now();
        return data.isBefore(now);
    }

    public UsuarioDtoResponse cadastrar(UsuarioModel usuarioModel) {
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

        usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + id));
        usuarioRepository.save(usuarioModel);

        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(usuarioModel.getId()
                , usuarioModel.getCategoria(), usuarioModel.getTipoDaDeficiencia(), usuarioModel.getNome(),
                usuarioModel.getEmail(), usuarioModel.getLatitude(),
                usuarioModel.getLongitude());

        return usuarioDtoResponse;

    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

}
