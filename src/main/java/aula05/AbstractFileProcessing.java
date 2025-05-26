package aula05;

/**
 * @author Manoel Campos
 */
public abstract class AbstractFileProcessing {
    public static final String DEFAULT_FILE_LINE = "Esta é uma linha de exemplo para benchmark de leitura de arquivo.\n";
    public static final String FILE_NAME = "target/benchmark.txt";

    protected static double executionTime(long start) {
        return (System.currentTimeMillis() - start) / 1000.0;
    }
}
