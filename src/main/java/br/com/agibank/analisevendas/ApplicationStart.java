package br.com.agibank.analisevendas;

import br.com.agibank.analisevendas.listener.DiretorioArquivosVendasListener;

import java.io.IOException;

public class ApplicationStart {

    public static void main(String[] args) {
        try {
            DiretorioArquivosVendasListener.monitoraDiretorioArquivoVendas();
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
