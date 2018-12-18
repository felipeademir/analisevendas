package br.com.agibank.analisevendas.listener;

import br.com.agibank.analisevendas.service.ILeituraArquivoService;
import br.com.agibank.analisevendas.service.LeituraArquivoService;

import java.io.IOException;
import java.nio.file.*;

public class DiretorioArquivosVendasListener {

    private static ILeituraArquivoService leituraArquivoService = new LeituraArquivoService();

    public static void monitoraDiretorioArquivoVendas() throws IOException {

        Path filePath = Paths.get("C:\\temp\\data\\in\\");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        filePath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        System.out.println("Watcher Registrado");
        processaEventos(watchService);
    }

    private static void processaEventos(WatchService watchService) {
        WatchKey watchKey;
        while (true) {
            try {
                System.out.println("Take");
                watchKey = watchService.take();
            } catch (InterruptedException ex) {
                return;
            }
            System.out.println("Pool");
            watchKey
                    .pollEvents()
                    .forEach(event -> {
                        Path path = (Path) event.context();
                        System.out.println("PROCESSA");
                        leituraArquivoService.processaArquivo(path.getFileName());

                    });

            watchKey.reset();
        }

    }
}
