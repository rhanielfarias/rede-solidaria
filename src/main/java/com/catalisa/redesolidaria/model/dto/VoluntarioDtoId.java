package com.catalisa.redesolidaria.model.dto;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.Enums.TiposDeDeficiencias;
import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioDtoId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Categorias categoria;
    private TiposDeDeficiencias deficiencias;
    private String nome;
    private String telefone;
    private String email;
    private double latitude;
    private double longitude;

private  Long idVoluntario;

}
