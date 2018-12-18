package br.com.agibank.model;

import br.com.agibank.analisevendas.model.ItemVenda;
import br.com.agibank.analisevendas.model.Venda;
import br.com.agibank.analisevendas.model.Vendedor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Tag("unit")
public class VendaTest {
    @Test
    @DisplayName("Deve retornar o preco total da venda")
    public void deveRetornarQuantidadeDeClientes() {
        Assert.assertEquals(getMockVenda().get(0).getPrecoTotalVenda(), 50, 0);
    }

    public List<Venda> getMockVenda(){
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda(1L, getMockItensVenda(), new Vendedor("123456780", "Felipe", new BigDecimal(0), 0)));
        return vendas;
    }

    public List<ItemVenda> getMockItensVenda(){
        List<ItemVenda> itensVenda = new ArrayList<>();
        itensVenda.add(new ItemVenda(1L, 3, new BigDecimal(10)));
        itensVenda.add(new ItemVenda(2L, 2, new BigDecimal(10)));
        return itensVenda;
    }
}
