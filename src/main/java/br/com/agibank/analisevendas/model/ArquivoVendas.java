package br.com.agibank.analisevendas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArquivoVendas {

    private String nomeArquivo;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<Venda> vendas;

    public int getQuantidadeClientes(){
        if(Objects.nonNull(clientes)){
            return clientes.size();
        }
        return 0;
    }

    public int getQuantidadeVendedores(){
        if(Objects.nonNull(vendedores)){
            return vendedores.size();
        }
        return 0;
    }

    public Venda getVendaMaisCara(){
        if(Objects.nonNull(vendas)){
            return vendas.stream()
                    .sorted(Comparator.comparingDouble(Venda::getPrecoTotalVenda).reversed())
                    .collect(Collectors.toList())
                    .get(0);
        }
        return null;
    }

    public Vendedor getPiorVendedor(){
        if(Objects.nonNull(vendedores)){
            return vendedores.stream()
                    .sorted(Comparator.comparingDouble(Vendedor::getTotalVendido))
                    .collect(Collectors.toList())
                    .get(0);
        }
        return null;
    }
}
