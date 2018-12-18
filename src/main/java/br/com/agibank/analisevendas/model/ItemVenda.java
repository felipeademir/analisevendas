package br.com.agibank.analisevendas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
public class ItemVenda {

    private Long idItemVenda;
    private int quantidade;
    private BigDecimal precoItem;

    public BigDecimal getPrecoTotalItem(){
        return precoItem.multiply(new BigDecimal(quantidade));
    }

}
