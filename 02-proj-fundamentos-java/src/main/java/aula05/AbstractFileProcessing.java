package aula05;

/**
 * Classe base usado pelas classes de geração e leitura de arquivos.
 * @author Manoel Campos
 */
public abstract class AbstractFileProcessing {
    public static final String DEFAULT_FILE_LINE = "Esta é uma linha de exemplo para benchmark de leitura de arquivo.\n";
    public static final String FILE_NAME = "target/benchmark.txt";

    /**
     * Obtém o tempo de execução de um método a partir do tempo inicial.
     * @param startTimeMillis tempo inicial em milissegundos
     * @return tempo total de execução em segundos
     */
    protected static double executionTime(final long startTimeMillis) {
        return (System.currentTimeMillis() - startTimeMillis) / 1000.0;
    }
}
