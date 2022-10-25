package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoSolicitacao;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import com.catalisa.redesolidaria.security.SecurityConfiguration;
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
                usuario.getCategoria(), usuario.getDeficiencias(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getLatitude(),
                usuario.getLongitude())).collect(Collectors.toList());
    }

    public List<UsuarioDtoResponse> buscarID(Long id) {
        Optional<UsuarioModel> usuario = Optional.of(usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id não encontrado" + id)));
        return usuario.stream().map(usuarioModel -> new UsuarioDtoResponse(usuario.get().getId(),
                usuario.get().getCategoria(), usuario.get().getDeficiencias(), usuario.get().getNome(),
                usuario.get().getTelefone(), usuario.get().getEmail(),
                usuario.get().getLatitude(), usuario.get().getLongitude())).collect(Collectors.toList());
    }

    public boolean validadorDeMenorDeIdade(UsuarioModel usuarioModel) {
        LocalDate data = usuarioModel.getDataDeNascimento().plusYears(18);
        LocalDate now = LocalDate.now();
        return data.isBefore(now);
    }


    public UsuarioDtoResponse cadastrar(UsuarioModel usuarioModel) {

        Boolean validandoIdade = validadorDeMenorDeIdade(usuarioModel);
        usuarioModel.setSenha(SecurityConfiguration.passwordEncoder().encode(usuarioModel.getSenha()));
        if (validandoIdade) {
            usuarioRepository.save(usuarioModel);
            UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(usuarioModel.getId()
                    , usuarioModel.getCategoria(), usuarioModel.getDeficiencias(), usuarioModel.getNome(),
                    usuarioModel.getTelefone(), usuarioModel.getEmail(), usuarioModel.getLatitude(),
                    usuarioModel.getLongitude());

            return usuarioDtoResponse;
        } else {
            return null;
        }
    }

    public UsuarioDtoResponse atualizar(UsuarioModel usuarioModel, Long id) {

        UsuarioModel atualizar = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ID not found " + id));
        atualizar.setLatitude(usuarioModel.getLatitude());
        atualizar.setLongitude(usuarioModel.getLongitude());

        usuarioRepository.save(atualizar);

        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse(atualizar.getId()
                , atualizar.getCategoria(), atualizar.getDeficiencias(), atualizar.getNome(), atualizar.getTelefone(),
                atualizar.getEmail(), atualizar.getLatitude(),
                atualizar.getLongitude());

        return usuarioDtoResponse;
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }


    public UsuarioDtoSolicitacao solicitarAjuda(Long id) {

        double menorDistancia = Double.MAX_VALUE;
        UsuarioModel voluntarioMaisProximo;

        UsuarioModel usuarioSolicitante = usuarioRepository.findById(id).get();
        List<UsuarioModel> usuarioVoluntarios = usuarioRepository.findByCategoria(Categorias.VOLUNTARIO);

        for (UsuarioModel voluntario : usuarioVoluntarios) {
            double distancia = CaculadoresDeDistancia.calculaDistancia(usuarioSolicitante.getLatitude(),
                    usuarioSolicitante.getLongitude(),
                    voluntario.getLatitude(), voluntario.getLongitude());
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                voluntarioMaisProximo = voluntario;
                UsuarioDtoSolicitacao voluntarioSelecionado = new UsuarioDtoSolicitacao(voluntario.getId(), voluntario.getNome(),
                        voluntario.getTelefone());
                return voluntarioSelecionado;
            }
        }
        throw new RuntimeException("Nenhum voluntário encontrado!");
    }

}
