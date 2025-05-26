package aula05;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Manoel Campos
 */
public class FileGenerator extends AbstractFileProcessing {
    private static final long MAX_FILE_SIZE = 200 * 1024 * 1024; // 200 MB

    public static void main(String[] args) {
        final FileProcessingResult res = writeFile();
        System.out.println("Tempo de gravação: " + res);
    }

    private static FileProcessingResult writeFile() {
        long fileSize = 0, lines = 0;
        final long start = System.currentTimeMillis();

        try(final var writter = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            while(fileSize < MAX_FILE_SIZE){
                writter.write(DEFAULT_FILE_LINE);
                lines++;
                fileSize += DEFAULT_FILE_LINE.length();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final double executionTime = executionTime(start);
        return new FileProcessingResult(lines, executionTime);
    }

}
