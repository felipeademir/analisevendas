package br.com.agibank.analisevendas.service;

import br.com.agibank.analisevendas.model.ArquivoVendas;

public interface ILeituraArquivoService {

    ArquivoVendas processaArquivo(String fileName);

}
