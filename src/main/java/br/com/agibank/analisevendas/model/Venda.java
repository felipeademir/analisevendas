package br.com.agibank.analisevendas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class Venda {

    private Long idVenda;
    private List<ItemVenda> itensVenda;
    private Vendedor vendedor;
}
