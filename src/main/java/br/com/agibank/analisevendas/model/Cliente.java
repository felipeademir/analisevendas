package br.com.agibank.analisevendas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Setter
@Getter
public class Cliente {

    private String cnpj;
    private String nome;
    private String areaDeNegocio;

}
