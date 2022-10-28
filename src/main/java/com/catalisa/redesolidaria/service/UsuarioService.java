package com.catalisa.redesolidaria.service;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.exceptions.ServiceExc;
import com.catalisa.redesolidaria.model.UsuarioModel;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoResponse;
import com.catalisa.redesolidaria.model.dto.UsuarioDtoSolicitacao;
import com.catalisa.redesolidaria.model.dto.VoluntarioDtoId;
import com.catalisa.redesolidaria.repository.UsuarioRepository;
import com.catalisa.redesolidaria.security.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
private  UsuarioModel usuarioModel;

    public List<VoluntarioDtoId>     buscarVoluntarioId(Long id) {
        List<UsuarioModel> buscarUsuario = usuarioRepository.findAll();
        return buscarUsuario.stream().map(usuario -> new VoluntarioDtoId(usuario.getId(), usuario.getCategoria(), usuario.getDeficiencias(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getLatitude(), usuario.getLongitude(), usuario.getIdVoluntario())).collect(Collectors.toList());
    }


    public List<UsuarioDtoResponse> buscar() {
        List<UsuarioModel> buscarUsuario = usuarioRepository.findAll();
        return buscarUsuario.stream().map(usuario -> new UsuarioDtoResponse(usuario.getId(),
                usuario.getCategoria(), usuario.getDeficiencias(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getLatitude(),
                usuario.getLongitude())).collect(Collectors.toList());
    }

    public UsuarioDtoResponse buscarID(Long id) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("id não encontrado" + id));
      return   new UsuarioDtoResponse(usuario.getId(),
                usuario.getCategoria(), usuario.getDeficiencias(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getLatitude(),
                usuario.getLongitude());
    }

    public boolean validadorDeMenorDeIdade(UsuarioModel usuarioModel) {
        LocalDate data = usuarioModel.getDataDeNascimento().plusYears(18);
        LocalDate now = LocalDate.now();
        return data.isBefore(now);
    }


    public UsuarioDtoResponse cadastrar(UsuarioModel usuarioModel) throws Exception {

        Boolean validandoIdade = validadorDeMenorDeIdade(usuarioModel);
        usuarioModel.setSenha(Criptografia.md5(usuarioModel.getSenha()));
        //usuarioModel.setSenha(SecurityConfiguration.passwordEncoder().encode(usuarioModel.getSenha()));
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
                usuarioModel.setIdVoluntario(voluntarioSelecionado.getId());
                return voluntarioSelecionado;
            }
        }
        throw new RuntimeException("Nenhum voluntário encontrado!");
    }

    public List<UsuarioDtoSolicitacao> buscaVoluntarios(UsuarioModel usuarioModel){
       List<UsuarioModel> usuarios =  usuarioRepository.findByCategoria(Categorias.VOLUNTARIO);
       List<UsuarioDtoSolicitacao> usuariosDto = new ArrayList<>();
        for (UsuarioModel u:usuarios) {
            UsuarioDtoSolicitacao novoUsuario = new UsuarioDtoSolicitacao(u.getId(),u.getNome(),u.getTelefone());
            usuariosDto.add(novoUsuario);
        }
       return usuariosDto;
    }

    public UsuarioModel loginUser(String login, String senha) throws ServiceExc {
        UsuarioModel userLogin =usuarioRepository.buscarLogin(login, senha);
        return userLogin;
    }

    public UsuarioModel buscarUsuarioPcd(Long id){
        List<UsuarioModel> listaDeusuarios = usuarioRepository.findByCategoria(Categorias.USUARIO);
        for (UsuarioModel u: listaDeusuarios) {
            if(Objects.equals(u.getId(), id)){
                return u;
            }else {
                throw new RuntimeException("Nenhum usuário encontrado");
            }
        }
        throw new RuntimeException("Nenhum usuário encontrado");
    }

}
