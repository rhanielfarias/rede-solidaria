package com.catalisa.redesolidaria.repository;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    public List<UsuarioModel> findByCategoria(Categorias categorias);


}
