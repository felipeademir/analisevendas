package br.com.agibank.analisevendas.listener;

import br.com.agibank.analisevendas.service.GerarRelatorioService;
import br.com.agibank.analisevendas.service.IGerarRelatorioService;
import br.com.agibank.analisevendas.service.ILeituraArquivoService;
import br.com.agibank.analisevendas.service.LeituraArquivoService;

import java.io.IOException;
import java.nio.file.*;

public class DiretorioArquivosVendasListener {

    private static ILeituraArquivoService leituraArquivoService = new LeituraArquivoService();
    private static IGerarRelatorioService gerarRelatorioService = new GerarRelatorioService();

    public static void monitoraDiretorioArquivoVendas() throws IOException {
        Path filePath = Paths.get(leituraArquivoService.getDiretorioWatcher());
        WatchService watchService = FileSystems.getDefault().newWatchService();
        filePath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        processaEventos(watchService);
    }

    private static void processaEventos(WatchService watchService) {
        WatchKey watchKey;
        while (true) {
            try {
                watchKey = watchService.take();
            } catch (InterruptedException ex) {
                return;
            }
            watchKey
                    .pollEvents()
                    .forEach(event -> {
                        Path path = (Path) event.context();
                        gerarRelatorioService.geraRelatorioArquivoVenda(
                                leituraArquivoService.processaArquivo(path.getFileName()));
                    });

            watchKey.reset();
        }
    }
}
