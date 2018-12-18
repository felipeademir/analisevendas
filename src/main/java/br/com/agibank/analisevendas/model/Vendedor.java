package br.com.agibank.analisevendas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
public class Vendedor {

    private String cpf;
    private String nome;
    private BigDecimal salario;
    private double totalVendido;

    public void setTotalVendido(double valorVenda){
        this.totalVendido += valorVenda;
    }


}
