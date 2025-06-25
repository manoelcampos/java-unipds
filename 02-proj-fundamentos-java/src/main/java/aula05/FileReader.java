package aula05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 * @author Manoel Campos
 */
public class FileReader extends AbstractFileProcessing {
    public static void main(String[] args) throws IOException {
        System.out.println("Tempo de leitura com API clássica                                       : " + readFileClassic());

        // Tamanho do buffer para leitura com NIO
        final int largeBufferSize = 8192;
        final int lineLenBufferSize = DEFAULT_FILE_LINE.length();

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
        // Usa um try(), chamado de try-with-resources, para garantir que o channel seja fechado automaticamente ao final do bloco
        try (final var channel = FileChannel.open(Paths.get(FILE_NAME), StandardOpenOption.READ)) {
            final var buffer = ByteBuffer.allocate(bufferSize);
            while (channel.read(buffer) != -1) {
                /* Só temos como saber o total de linhas lidas se
                * pegarmos o buffer e procurarmos um \n dentro dele, mas isso vai aumentar o tempo de processamento.
                * Considerando que todas as linhas têm o mesmo tamanho,
                * poderíamos calcular o total de linhas usando bufferSize/DEFAULT_FILE_LINE.
                * Se temos um buffer de 150 bytes e o tamanho da linha é 100,
                * a cada leitura do arquivo, estamos lendo 1.5 linha.
                * Somando estes valores, temos o total de linhas.
                * Mas isso atualmente não funciona, pois um caractere em uma String em Java
                * pode ocupar 1 ou 2 bytes, depois da implementação de Compact Strings no JDK 9.
                * Ver https://openjdk.org/jeps/254.
                * Poderíamos pegar o buffer e converter para uma String, usando
                * o length() para saber quantos caracteres tem.
                * Mas isso também vai aumentar o tempo de execução. */

                // ----------------------------- Simula processamento da linha -----------------------------------------
                /*
                // Só precisa do buffer.flip() se for mudar o buffer do modo de escrita para o de leitura
                buffer.flip();
                final var line = new String(buffer.array(), 0, buffer.limit());
                System.out.println(line);
                buffer.clear();
                */

            }
        }

        final double executionTime = executionTime(start);
        return new FileProcessingResult(executionTime);
    }

    /**
     * Le um arquivo usando a API NIO2 com o método {@link Files#readAllLines(Path)}.
     * @return um objeto {@link FileProcessingResult} com o total de linhas lidas e o tempo de execução.
     * @throws IOException
     */
    private static FileProcessingResult readFileNIO2() throws IOException {
        final long start = System.currentTimeMillis();
        final var lines = Files.readAllLines(Paths.get(FILE_NAME));

        try(var linesStream = Files.lines(Paths.get(FILE_NAME))){
            linesStream.forEach(System.out::println);
        }

        // Simula processamento da linha
        // System.out.println(lines); // Descomente para ver o conteúdo do arquivo
        final double executionTime = executionTime(start);
        return new FileProcessingResult(lines.size(), executionTime);
    }
}
