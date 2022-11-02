package com.catalisa.redesolidaria.model.dto;

import com.catalisa.redesolidaria.Enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Categorias categoria;
    private String nome;

}
