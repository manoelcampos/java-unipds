package aula05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Manoel Campos
 */
public class FileReader extends AbstractFileProcessing {
    public static void main(String[] args) throws IOException {
        System.out.println("Tempo de leitura com API clássica                                       : " + readFileClassic());

        // Tamanho do buffer para leitura com NIO
        final int largeBufferSize = 8192;
        // +1 para ler até o \n do final da linha (pois sabemos o tamanho de cada linha)
        final int lineLenBufferSize = DEFAULT_FILE_LINE.length() + 1;

        System.out.println("Tempo de leitura com NIO/FileChannel e buffer grande                    : " + readFileNIO(largeBufferSize));
        System.out.println("Tempo de leitura com NIO/FileChannel e buffer menor (igual a line.len()): " + readFileNIO(lineLenBufferSize));
        System.out.println("Tempo de leitura com NIO2                                               : " + readFileNIO2());
    }

    /**
     * Le um arquivo usando um objeto {@link BufferedReader}.
     * @return um objeto {@link FileProcessingResult} com o total de linhas lidas e o tempo de execução.
     * @throws IOException
     */
    private static FileProcessingResult readFileClassic() throws IOException {
        final long start = System.currentTimeMillis();
        int lines = 0;
        // Usa um try(), chamado de try-with-resources, para garantir que o reader seja fechado automaticamente ao final do bloco
        // Usa o Files.newBufferedReader() que simplifica a criação de um objeto BufferedReader
        try (final var reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                // Simula processamento da linha
                // System.out.println(line); // Descomente para ver o conteúdo do arquivo
            }
        }

        final double executionTime = executionTime(start);
        return new FileProcessingResult(lines, executionTime);
    }

    /**
     * Le um arquivo usando a API NIO (New I/O) com um objeto {@link FileChannel}.
     * @param bufferSize tamanho do buffer para armazenar os dados lidos. Quanto maior o buffer, mais eficiente será a leitura.
     * @return um objeto {@link FileProcessingResult} com o total de linhas lidas e o tempo de execução.
     * @throws IOException
     */
    private static FileProcessingResult readFileNIO(final int bufferSize) throws IOException {
        final long start = System.currentTimeMillis();
        int lines = -1;
        // Usa um try(), chamado de try-with-resources, para garantir que o channel seja fechado automaticamente ao final do bloco
        try (final var channel = FileChannel.open(Paths.get(FILE_NAME), StandardOpenOption.READ)) {
            final var buffer = ByteBuffer.allocate(bufferSize);
            while (channel.read(buffer) != -1) {
                /* Só temos como saber o total de linhas lidas se lermos o total de chars de cada linha por vez
                * (a não ser que peguemos o buffer e procuremos um \n dentro dele, mas isso vai aumentar o tempo
                * de processamento).*/
                if(bufferSize == DEFAULT_FILE_LINE.length() + 1)
                    lines++;

                // ----------------------------- Simula processamento da linha -----------------------------------------
                /*
                // Só precisa do buffer.flip() aqui, para mudar o buffer do modo de escrita para o de leitura
                buffer.flip();
                final var line = new String(buffer.array(), 0, buffer.limit());
                System.out.println(line);
                */

                buffer.clear();
            }
        }

        /* Se lines for diferente de -1, indica que sabemos o total de linhas linhas.
        * No entanto, como iniciamos a variável com -1 no lugar de zero, é preciso somar 1 aqui.
        * O valor foi iniciado com -1 para indicar quando não conseguimos saber quando
        * o total de linhas do arquivo não pode ser descoberto. */
        if(lines > -1)
            lines++;

        final double executionTime = executionTime(start);
        return new FileProcessingResult(lines, executionTime);
    }

    /**
     * Le um arquivo usando a API NIO2 com o método {@link Files#readAllLines(Path)}.
     * @return um objeto {@link FileProcessingResult} com o total de linhas lidas e o tempo de execução.
     * @throws IOException
     */
    private static FileProcessingResult readFileNIO2() throws IOException {
        final long start = System.currentTimeMillis();
        final var lines = Files.readAllLines(Paths.get(FILE_NAME));

        // Simula processamento da linha
        // System.out.println(lines); // Descomente para ver o conteúdo do arquivo
        final double executionTime = executionTime(start);
        return new FileProcessingResult(lines.size(), executionTime);
    }
}
