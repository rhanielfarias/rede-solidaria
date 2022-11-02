package com.catalisa.redesolidaria.model;

import com.catalisa.redesolidaria.Enums.Categorias;
import com.catalisa.redesolidaria.Enums.TiposDeDeficiencias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

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

    @NotBlank(message = "Informe o nome")
    private String nome;

    @NotBlank(message = "Colocar CPF válido")
    @CPF
    private String cpf;

    @Column(length = 50, nullable = false)
    private LocalDate dataDeNascimento;

    @NotBlank(message = "Colocar e-mail válido")
    @Email
    private String email;

    @Column(length = 30, nullable = false)
    private String telefone;

    @Column
    @Enumerated(EnumType.STRING)
    private Categorias categoria;


    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column
    private TiposDeDeficiencias deficiencias;

    @Column
    private Long idVoluntario;

}
