package br.com.agibank.model;

import br.com.agibank.analisevendas.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Tag("unit")
public class ArquivoVendasTest{

    @Test
    @DisplayName("Deve retornar a quantidade de clientes")
    public void deveRetornarQuantidadeDeClientes() {
        Assert.assertEquals(getMockArquivoVenda().getQuantidadeClientes(), 2);
    }

    @Test
    @DisplayName("Deve retornar a quantidade de vendedores")
    public void deveRetornarQuantidadeDeVendedores() {
        Assert.assertEquals(getMockArquivoVenda().getQuantidadeVendedores(), 3);
    }

    @Test
    @DisplayName("Deve identificar a venda mais cara")
    public void deveIdentificarVendaMaisCara() {
        Assert.assertEquals(getMockArquivoVenda().getVendaMaisCara().getPrecoTotalVenda(), 30, 0);
    }

    @Test
    @DisplayName("Deve identificar o pior vendedor")
    public void deveIdentificarPiorVendedor() {
        Assert.assertEquals(getMockArquivoVenda().getPiorVendedor().getNome(), "Felipe");
    }


    public ArquivoVendas getMockArquivoVenda(){
        return new ArquivoVendas(
                "unitTest.dat",
                getMockVendedores(),
                getMockCliente(),
                getMockVenda());
    }

    public List<Vendedor> getMockVendedores(){
        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(new Vendedor("01234568798", "Joao", new BigDecimal(10000), 100));
        vendedores.add(new Vendedor("01234568898", "Felipe", new BigDecimal(5000), 0));
        vendedores.add(new Vendedor("11234568898", "Claudio", new BigDecimal(1000), 100));
        return vendedores;
    }

    public List<Cliente> getMockCliente(){
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("01234567890123", "Fulano", "Rural"));
        clientes.add(new Cliente("01234567891123", "Ciclano", "Comercial"));
        return clientes;
    }

    public List<Venda> getMockVenda(){
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda(1L, getMockItensVenda(), getMockVendedores().get(0)));
        return vendas;
    }

    public List<ItemVenda> getMockItensVenda(){
        List<ItemVenda> itensVenda = new ArrayList<>();
        itensVenda.add(new ItemVenda(1L, 1, new BigDecimal(10)));
        itensVenda.add(new ItemVenda(2L, 2, new BigDecimal(10)));
        return itensVenda;
    }


}
