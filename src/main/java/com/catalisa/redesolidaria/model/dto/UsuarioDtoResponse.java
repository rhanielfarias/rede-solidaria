package com.catalisa.redesolidaria.model.dto;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Categorias categoria;
    private String nome;
    private String email;
    private double latitude;
    private double longitude;
}
