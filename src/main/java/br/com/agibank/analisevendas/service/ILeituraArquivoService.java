package br.com.agibank.analisevendas.service;

import br.com.agibank.analisevendas.model.ArquivoVendas;

import java.io.IOException;
import java.nio.file.Path;

public interface ILeituraArquivoService {

    ArquivoVendas processaArquivo(Path fileName);
    String getDiretorioWatcher() throws IOException;

}
