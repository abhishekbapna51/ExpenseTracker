package main.java.com.expensetracker.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void ensureFileExists(Path path, String header) throws IOException {
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) Files.createDirectories(parent);
        if (!Files.exists(path)) {
            Files.write(path, header.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
    }

    public static List<String> readAllLines(Path path) {
        try {
            if (!Files.exists(path)) return new ArrayList<>();
            return Files.readAllLines(path);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void appendLine(Path path, String line) {
        try {
            ensureFileExists(path, "ID,Amount,Category,Date,Description\n");
            Files.write(path, (line + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void writeAllLines(Path path, List<String> lines) {
        try {
            ensureFileExists(path, "ID,Amount,Category,Date,Description\n");
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
