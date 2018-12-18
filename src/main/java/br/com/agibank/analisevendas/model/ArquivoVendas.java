package br.com.agibank.analisevendas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArquivoVendas {

    private String nomeArquivo;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<Venda> vendas;

}
