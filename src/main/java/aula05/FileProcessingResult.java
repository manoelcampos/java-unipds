package aula05;

/**
 * Armazena dados sobre a leitura ou gravação de um arquivo.
 * @param linesNumber total de linhas processadas ou -1 para indicar que não foi possível identificar o total de linhas
 * @param processingTimeSecs tempo de processamento de todo o arquivo (sem segundos)
 * @author Manoel Campos
 */
public record FileProcessingResult(long linesNumber, double processingTimeSecs) {
    /**
     * Instancia um objeto sem indicar o total de linhas processadas
     * (armazena -1 para indicar que não foi possível identificar o total de linhas).
     */
    public FileProcessingResult(final double processingTimeSecs) {
        this(-1, processingTimeSecs);
    }

    @Override
    public String toString() {
        final var lines = linesNumber == -1 ? "Não Identificado" : String.format("%,d", linesNumber);
        return "%.2f segundos. Total de Linhas: %s".formatted(processingTimeSecs, lines);
    }
}
