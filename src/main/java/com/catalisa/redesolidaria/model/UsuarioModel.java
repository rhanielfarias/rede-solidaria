package com.catalisa.redesolidaria.model;

import com.catalisa.redesolidaria.controller.TipoDaDeficienciaController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

@Column
    private  String nome;

@Column
    private  String cpf;

@Column
    private LocalDate dataDeNascimento;

@Column
    private  String email;

@OneToMany(mappedBy = "usuarioModel", cascade = CascadeType.ALL)
private  List<TipoDaDeficienciaModel> tipoDaDeficienciaModels;
@Column
    private  double latitude;

@Column
    private  double longitude;
}
