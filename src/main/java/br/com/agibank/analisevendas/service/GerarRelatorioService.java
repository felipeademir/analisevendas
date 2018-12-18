package br.com.agibank.analisevendas.service;

import br.com.agibank.analisevendas.model.ArquivoVendas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class GerarRelatorioService implements IGerarRelatorioService{

    public static final String EXTENSAO_RELATORIO = ".done.dat";

    public void geraRelatorioArquivoVenda(ArquivoVendas arquivoVendas){
        String conteudoRelatorio;

        if(Objects.isNull(arquivoVendas)){
            return;
        }

        conteudoRelatorio = "Quantidade de Clientes: " + arquivoVendas.getQuantidadeClientes() + "\n";
        conteudoRelatorio += "Quantidade de Vendedores: " + arquivoVendas.getQuantidadeVendedores() + "\n";
        conteudoRelatorio += "Identificador da venda mais cara: " + arquivoVendas.getVendaMaisCara().getIdVenda() + "\n";
        conteudoRelatorio += "O pior vendedor: " + arquivoVendas.getPiorVendedor().getNome();

        Path path = Paths.get(formataNomeRelatorio(arquivoVendas));
        try {
            Files.write(path, conteudoRelatorio.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private String getDiretorioRelatorio() throws IOException {
        return new File(".").getCanonicalPath().concat("\\data\\out\\");
    }

    private String formataNomeRelatorio(ArquivoVendas arquivoVendas){
        try {
            return getDiretorioRelatorio()
                    .concat(arquivoVendas
                            .getNomeArquivo()
                            .substring(0, arquivoVendas.getNomeArquivo().length() - 4)
                            .concat(EXTENSAO_RELATORIO));
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

}
