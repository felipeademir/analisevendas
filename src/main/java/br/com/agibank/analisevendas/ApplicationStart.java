package br.com.agibank.analisevendas;

import br.com.agibank.analisevendas.listener.DiretorioArquivosVendasListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApplicationStart {

    public static void main(String[] args) {
        try {
            DiretorioArquivosVendasListener.monitoraDiretorioArquivoVendas();
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
