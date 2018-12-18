package br.com.agibank.model;

import br.com.agibank.analisevendas.model.ItemVenda;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.math.BigDecimal;

@Tag("unit")
public class ItemVendaTest {

    @Test
    @DisplayName("Deve retornar preço total do item")
    public void deveRetornarPrecoTotalDoItem() {
        ItemVenda itemVenda = new ItemVenda(1L, 2, new BigDecimal(10 ));
        Assert.assertEquals(itemVenda.getPrecoTotalItem(), new BigDecimal(20));
    }
}
