package com.catalisa.redesolidaria.model;

import com.catalisa.redesolidaria.Enums.Categorias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column //posteriormente colocar a validação @CPF
    private String cpf;

    @Column
    private LocalDate dataDeNascimento;

    @Column //posteriormente colocar a validação @Email
    private String email;

    @Column
    private Categorias categoria;


    @OneToMany(cascade = CascadeType.PERSIST)// por 'tipo de deficiencias' ser uma lista o Json deve ser preenchido como uma lista.
    private List<TipoDaDeficienciaModel> tipoDaDeficienciaModels;


    @Column
    private double latitude;

    @Column
    private double longitude;
}
