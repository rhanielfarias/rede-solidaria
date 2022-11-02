package com.catalisa.redesolidaria.model.dto;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.Enums.TiposDeDeficiencias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Categorias categoria;
    private TiposDeDeficiencias deficiencias;
    private String nome;
    private String telefone;
    private String email;
    private double latitude;
    private double longitude;
}
