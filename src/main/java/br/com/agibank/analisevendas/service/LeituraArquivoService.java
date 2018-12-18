package br.com.agibank.analisevendas.service;

import br.com.agibank.analisevendas.model.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraArquivoService implements ILeituraArquivoService {

    private ArquivoVendas arquivoVendas;

    public static final String LINHA_VENDEDORES = "001";
    public static final String LINHA_CLIENTES = "002";
    public static final String LINHA_VENDAS = "003";
    public static final String SEPARADOR_ARQUIVO = "ç";
    public static final String SEPARADOR_ITENS_VENDA = ",";
    public static final String SEPARADOR_DETALHE_ITEM = "-";

    public LeituraArquivoService() {
        this.arquivoVendas = new ArquivoVendas();
    }

    public ArquivoVendas processaArquivo(Path fileName) {
        if (!arquivoValido(fileName)) {
            return null;
        }
        arquivoVendas.setNomeArquivo(fileName.toString());
        processaVendedores(montaStream(fileName));
        processaClientes(montaStream(fileName));
        processaVendas(montaStream(fileName));

        return arquivoVendas;
    }

    public String getDiretorioWatcher() throws IOException {
        return new File(".").getCanonicalPath().concat("\\data\\in\\");
    }

    private boolean arquivoValido(Path fileName) {
        return fileName.toString().endsWith(".dat");
    }

    private Stream<String> montaStream(Path fileName) {
        try {
            return Files.lines(
                    Paths.get(getDiretorioWatcher() + fileName.toString()), Charset.forName("Cp1252"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void processaVendedores(Stream<String> stream) {

        List<Vendedor> listaVendedores = new ArrayList<>();
        identificaVendedoresNoArquivo(stream).forEach(vendedor ->
        {
            String[] dadosVendedor = vendedor.split(SEPARADOR_ARQUIVO);
            listaVendedores.add(new Vendedor(dadosVendedor[1], dadosVendedor[2], new BigDecimal(dadosVendedor[3]), 0));
        });
        arquivoVendas.setVendedores(listaVendedores);
    }

    private List<String> identificaVendedoresNoArquivo(Stream<String> stream) {
        return stream
                .filter(line -> line.startsWith(LINHA_VENDEDORES))
                .collect(Collectors.toList());
    }

    private void processaClientes(Stream<String> stream) {

        List<Cliente> listaClientes = new ArrayList<>();
        identificaClientesNoArquivo(stream).forEach(cliente ->
        {
            String[] dadosCliente = cliente.split(SEPARADOR_ARQUIVO);
            listaClientes.add(new Cliente(dadosCliente[1], dadosCliente[2], dadosCliente[3]));
        });
        arquivoVendas.setClientes(listaClientes);
    }

    private List<String> identificaClientesNoArquivo(Stream<String> stream) {
        return stream
                .filter(line -> line.startsWith(LINHA_CLIENTES))
                .collect(Collectors.toList());
    }

    private void processaVendas(Stream<String> stream) {

        List<Venda> listaVendas = new ArrayList<>();
        identificaVendasNoArquivo(stream).forEach(venda ->
        {
            String[] dadosVenda = venda.split(SEPARADOR_ARQUIVO);
            listaVendas.add(new Venda(Long.valueOf(dadosVenda[1])
                    , processaItensVenda(dadosVenda[2])
                    , buscaVendedor(dadosVenda[3])));

            buscaVendedor(dadosVenda[3]).setTotalVendido(listaVendas.get(listaVendas.size() - 1).getPrecoTotalVenda());
        });
        arquivoVendas.setVendas(listaVendas);
    }

    private List<String> identificaVendasNoArquivo(Stream<String> stream) {
        return stream
                .filter(line -> line.startsWith(LINHA_VENDAS))
                .collect(Collectors.toList());
    }

    private List<ItemVenda> processaItensVenda(String stream) {
        List<ItemVenda> itensVenda = new ArrayList<>();
        Arrays.asList(stream
                .replaceAll("[\\[\\]\"]", "")
                .split(SEPARADOR_ITENS_VENDA))
                .forEach(item -> {
                    itensVenda.add(processaItemVenda(item));
                });
        return itensVenda;
    }

    private ItemVenda processaItemVenda(String item) {
        String[] detalheItem = item.split(SEPARADOR_DETALHE_ITEM);
        return new ItemVenda(Long.valueOf(detalheItem[0])
                , Integer.parseInt(detalheItem[1])
                , new BigDecimal(detalheItem[2]));
    }

    private Vendedor buscaVendedor(String nome) {
        return arquivoVendas
                .getVendedores()
                .stream()
                .filter(x -> x.getNome().equals(nome))
                .findFirst()
                .get();
    }
}
