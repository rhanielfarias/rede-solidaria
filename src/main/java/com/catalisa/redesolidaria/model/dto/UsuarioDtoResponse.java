package com.catalisa.redesolidaria.model.dto;

import com.catalisa.redesolidaria.model.TipoDaDeficienciaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoResponse {

    private Long id;
    private String nome;
    private String email;
    private double latitude;
    private double longitude;
}